package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.alphawizard.hdwallet.alphahdwallet.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;
import com.alphawizard.hdwallet.common.util.MyLogger;

public class FirstLaunchViewModule extends BaseViewModel {

    CreateWalletInteract createWalletInteract ;
    WalletRouter  walletRouter;

    public FirstLaunchViewModule(CreateWalletInteract createWalletInteract,
                                 WalletRouter  router)
    {
        this.createWalletInteract = createWalletInteract;
        walletRouter = router;
    }

    private final MutableLiveData<Wallet> createdWallet = new MutableLiveData<>();

    public LiveData<Wallet> observeCreatedWallet() {
        return createdWallet;
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

}
