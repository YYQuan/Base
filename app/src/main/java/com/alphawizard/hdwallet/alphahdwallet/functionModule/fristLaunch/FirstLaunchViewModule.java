package com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.widget.Toast;

import com.alphawizard.hdwallet.alphahdwallet.data.Local;
import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;
import com.alphawizard.hdwallet.common.base.ViewModule.entity.C;
import com.alphawizard.hdwallet.common.base.ViewModule.entity.ErrorEnvelope;
import com.alphawizard.hdwallet.common.util.Log;

import javax.inject.Inject;

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

    public LiveData<Wallet> createdWallet() {
        return createdWallet;
    }

    public void newWallet() {
        progress.setValue(true);
        createWalletInteract
                .create()
//				create 过程中没有throw 就回调success ,如果有throw  异常的话，那么就会掉error
                .subscribe(account -> {
//                    fetchWallets();
                    createdWallet.postValue(account);
                }, this::onCreateWalletError);
    }

    public void openWallet(Context context){
        walletRouter.open(context);
    }

    private void onCreateWalletError(Throwable throwable) {
        Log.d("onCreateWalletError" +throwable.getMessage());

//        createWalletError.postValue(new ErrorEnvelope(C.ErrorCode.UNKNOWN, null));
    }
}
