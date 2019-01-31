package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;


import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.RealmTestDBInteract;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.entity.db.TestDBBean;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;
import com.alphawizard.hdwallet.common.util.MyLogger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FirstLaunchViewModule extends BaseViewModel {

    CreateWalletInteract createWalletInteract ;
    WalletRouter  walletRouter;
    RealmTestDBInteract mRealmTestDBInteract;

    public FirstLaunchViewModule(CreateWalletInteract createWalletInteract,
                                 RealmTestDBInteract realmTestDBInteract,
                                 WalletRouter  router)
    {

        this.createWalletInteract = createWalletInteract;
        mRealmTestDBInteract = realmTestDBInteract;
        walletRouter = router;
    }

    private final MutableLiveData<Wallet> createdWallet = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addTestBoolean = new MutableLiveData<>();
    private final MutableLiveData<List<TestDBBean>> findAllTest = new MutableLiveData<>();

    public LiveData<Wallet> observeCreatedWallet() {
        return createdWallet;
    }
    public LiveData<Boolean> observeAddTestBoolean() {
        return addTestBoolean;
    }
    public LiveData<List<TestDBBean>> observeFindAllTestBoolean() {
        return findAllTest;
    }


    public void createNewWallet() {
        progress.setValue(true);
        createWalletInteract
                .create()
//				create 过程中没有throw 就回调success ,如果有throw  异常的话，那么就会掉error
                .subscribe(account -> {
                    createdWallet.postValue(new Wallet(account));
                }, this::onCreateWalletError);
    }

    private void onCreateWalletError(Throwable throwable) {
        MyLogger.jLog().d("onCreateWalletError" +throwable.getMessage());
    }


    public   void  openWallet(Context context){
        walletRouter.open(context);
    }

    public  void  addTestBean (TestDBBean  bean ){
        mRealmTestDBInteract.addTestBean(bean)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addTestBeanSuccess);
    }

    public  void  addTestBeans (List<TestDBBean>  bean ){
        mRealmTestDBInteract.addTestBeans(bean)
                .observeOn(Schedulers.io())
                .subscribe(this::addTestBeanSuccess);
    }


    private void addTestBeanSuccess(Boolean result) {
        addTestBoolean.postValue(result);
        MyLogger.jLog().d( "addTestBeanSuccess  result : "+ result );
    }

    public void  findAllTestBean(){
        mRealmTestDBInteract.findAllTestBean()
                .subscribe(this::findAllTestBeanResult,this::onError);
//        mRealmTestDBInteract.findEqaultTestBeanId("21")
//                .subscribe(this::findAllTestBeanResult);
//        mRealmTestDBInteract.findEqaultTestBeanAge(49)
//                .subscribe(this::findAllTestBeanResult);
//        mRealmTestDBInteract.findLessTestBeanAge(27)
//                .subscribe(this::findAllTestBeanResult1);
//        mRealmTestDBInteract.findGreaterTestBeanAge(27)
//                .subscribe(this::findAllTestBeanResult2);
    }

    private void findAllTestBeanResult2(List<TestDBBean> testDBBeans) {
        for(TestDBBean  bean : testDBBeans) {
            MyLogger.jLog().d("findGreaterTestBeanAge  id: " + bean.getId());
        }
    }

    private void findAllTestBeanResult1(List<TestDBBean> testDBBeans) {
        for(TestDBBean  bean : testDBBeans) {
            MyLogger.jLog().d("findLessTestBeanAge  id: "+bean.getId());
        }

    }

    private void findAllTestBeanResult(List<TestDBBean> realmObjects) {
        findAllTest.postValue(realmObjects);
    }

    private void findAllTestBeanResult(TestDBBean realmObjects) {
        MyLogger.jLog().d("findEqaultTestBeanId  id: "+realmObjects.getId());
    }


}
