package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;


import com.alphawizard.hdwallet.alphahdwallet.di.interact.RealmTestDBInteract;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.entity.db.TestDBBean;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.di.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;
import com.alphawizard.hdwallet.common.util.MyLogger;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmObject;
import io.realm.RealmResults;

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
    private final MutableLiveData<List<TestDBBean>> findAllTestBoolean = new MutableLiveData<>();

    public LiveData<Wallet> observeCreatedWallet() {
        return createdWallet;
    }
    public LiveData<Boolean> observeAddTestBoolean() {
        return addTestBoolean;
    }
    public LiveData<List<TestDBBean>> observeFindAllTestBoolean() {
        return findAllTestBoolean;
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
    private void addTestBeanSuccess(Boolean result) {
        addTestBoolean.postValue(result);
        MyLogger.jLog().d( "addTestBeanSuccess  result : "+ result );
    }

    public void  findAllTestBean(){
        mRealmTestDBInteract.findAll(TestDBBean.class)
                .subscribe(this::findAllTestBeanResult);
    }

    private void findAllTestBeanResult(RealmResults<TestDBBean> realmObjects) {
        findAllTestBoolean.postValue(realmObjects);
    }


}
