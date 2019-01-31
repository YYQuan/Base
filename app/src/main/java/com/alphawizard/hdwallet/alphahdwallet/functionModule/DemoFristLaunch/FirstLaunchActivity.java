package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alphawizard.hdwallet.alphahdwallet.App;
import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.constant.URLConstant;
import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.PrefsInsert;
import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.RealmsInsert;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor.SharedPreferenceRepository;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.entity.db.TestDBBean;
import com.alphawizard.hdwallet.common.base.App.ToolbarActivity;
import com.alphawizard.hdwallet.common.util.MyLogger;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@Route(path = "/test/activity/firstLaunch")
public class FirstLaunchActivity extends ToolbarActivity  {

    @BindView(R.id.btn_create_account)
    Button btnCreate;

    @BindView(R.id.btn_import_account)
    Button btnImportAccount;

    @Inject
    FirstLaunchViewModuleFactory walletsViewModuleFactory;
    FirstLaunchViewModule viewModel;

    @Autowired
    String  name ;

    @Override
    public int getContentLayoutID() {
        return R.layout.activity_first_launch;
    }

    @Override
    public void initFirst() {
        super.initFirst();
        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA};
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 走申请流程
//            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }


    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void initData() {
        super.initData();

        viewModel = ViewModelProviders.of(this, walletsViewModuleFactory)
                .get(FirstLaunchViewModule.class);

        viewModel.observeCreatedWallet().observe(this,this::onCreatedWallet);
        viewModel.observeAddTestBoolean().observe(this,this::obAddTest);
        viewModel.observeFindAllTestBoolean().observe(this,this::obFindAllTest);

        OkGo.<String>get(URLConstant.URL_BAIDU)
                .cacheKey(URLConstant.URLBAIDU_CACHE)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<>())
//                被观察者 在  io线程
                .subscribeOn(Schedulers.io())
//                初始化  在  subsrcribe 前被调用
                .doOnSubscribe((params)->{
                    MyLogger.jLog().d("网络访问  初始化");
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        MyLogger.jLog().d("网络访问  onSubscribe");
                    }

                    @Override
                    public void onNext(Response<String> stringResponse) {

                        if(stringResponse.isFromCache()) {
                            MyLogger.jLog().d("网络访问  从缓存中拿到值");
                            onTestAspectJ("网络访问  从缓存中拿到值");
                        }else{

                            MyLogger.jLog().d("网络访问  拿到最新值");
                            onTestAspectJ("网络访问  拿到最新值");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyLogger.jLog().d("网络访问  onError");
                    }

                    @Override
                    public void onComplete() {
                        MyLogger.jLog().d("网络访问  onComplete");
                    }
                });
        method1();
//

    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//    }



//    @HookMethod(beforeMethod = "method1",afterMethod = "method2")
//    @Async
//    @Safe(callBack = "doCallBack")
    private  void  onTestAspectJ(String str){

        MyLogger.jLog().d(str);

    }

    private static final int RC = 0x0100;

//    @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA},requestCode = RC)
//    @AfterPermissionGranted(RC)
//    当有权限时 才会执行

//    @RealmsInsert()
    private List<TestDBBean> method1() {
        MyLogger.jLog().e("  PrefsInsert  ");
        List<TestDBBean>  beans= new ArrayList<>();

        for(int  i =0  ;i<10;i++){
            beans.add(new TestDBBean("id"+i,"name "+i,i));

        }
        return  beans;
    }


//    @AfterPermissionGranted(RC)
    void  requestPerm(){
            App.showToast("callback    request perm");
        MyLogger.jLog().e("AfterPermissionGranted  10000000");

    }



    private void doCallBack(boolean  isTrue) {
        MyLogger.jLog().d("safe  doCallBack "+isTrue);
        Toast.makeText(this, "invoke the doCallBack method", Toast.LENGTH_SHORT).show();
    }

    private void method2() {
        MyLogger.jLog().d("method2() is called after initData()");
    }

    private void obFindAllTest(List<TestDBBean> realmObjects) {
        MyLogger.jLog().d("current    thread  ");
        Flowable.fromIterable(realmObjects)
                .observeOn(Schedulers.io())
                .subscribe(object ->  MyLogger.jLog().d("ob find all test "+object.getName()));
    }

    private void obAddTest(Boolean aBoolean) {
        App.showToast("obAddTest result :"+aBoolean);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_create_account)
    void onClickBtnCreate(){
        viewModel.createNewWallet();

//        viewModel.openWallet(this);

    }

    @OnClick(R.id.btn_import_account)
    void onClickBtnImport(){
        viewModel.findAllTestBean();
//        List  list =  new ArrayList();
//        Random random = new Random();
//        int number = random.nextInt(50);
//        for(int i = 0  ; i<5;i++) {
//            number  += i;
//            TestDBBean bean =new TestDBBean("" + number, "name_" + number, number);
//            list.add(bean);
//        }
//        viewModel.addTestBeans(list);
    }


    public void onCreatedWallet(Wallet wallet) {
        MyLogger.jLog().d("onCreatedWallet");
        viewModel.openWallet(this);
    }


//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN, priority = 300)
//    public void eventBusTest(String  str) {
//        App.showToast("Event Bus  test  :"+ str);
//        MyLogger.jLog().d("Event Bus  test  :"+ str);
//    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN, priority = 300)
    public void eventBusWallet(Wallet  wallet) {
        App.showToast("Event Bus  Wallet  :"+ wallet.address);
        MyLogger.jLog().d("Event Bus  Wallet  :"+ wallet.address);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


}
