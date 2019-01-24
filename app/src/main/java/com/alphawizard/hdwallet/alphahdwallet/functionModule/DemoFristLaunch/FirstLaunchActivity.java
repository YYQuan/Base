package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alphawizard.hdwallet.alphahdwallet.App;
import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.constant.URLConstant;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.common.base.App.ToolbarActivity;
import com.alphawizard.hdwallet.common.util.MyLogger;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.logging.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@Route(path = "/test/activity/firstLaunch")
public class FirstLaunchActivity extends ToolbarActivity {

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
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
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
                        }else{

                            MyLogger.jLog().d("网络访问  拿到最新值");
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
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_create_account)
    void onClickBtnCreate(){
        viewModel.createNewWallet();
    }

    @OnClick(R.id.btn_import_account)
    void onClickBtnImport(){

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
}
