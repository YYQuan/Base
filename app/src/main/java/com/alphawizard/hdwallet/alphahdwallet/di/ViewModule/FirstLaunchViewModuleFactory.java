package com.alphawizard.hdwallet.alphahdwallet.di.ViewModule;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchViewModule;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;

public class FirstLaunchViewModuleFactory  implements ViewModelProvider.Factory{

    CreateWalletInteract mInteract;
    WalletRouter walletRouter;

    public FirstLaunchViewModuleFactory(CreateWalletInteract interact,WalletRouter router) {
        mInteract = interact;
        walletRouter = router;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new FirstLaunchViewModule(mInteract,walletRouter);
    }
}
