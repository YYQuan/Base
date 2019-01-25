package com.alphawizard.hdwallet.alphahdwallet.di.ViewModule;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.alphawizard.hdwallet.alphahdwallet.di.interact.RealmTestDBInteract;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch.FirstLaunchViewModule;
import com.alphawizard.hdwallet.alphahdwallet.di.interact.CreateWalletInteract;

public class FirstLaunchViewModuleFactory  implements ViewModelProvider.Factory{

    CreateWalletInteract mInteract;
    WalletRouter walletRouter;
    RealmTestDBInteract mRealmTestDBInteract;

    public FirstLaunchViewModuleFactory(CreateWalletInteract interact,
                                        RealmTestDBInteract realmTestDBInteract,
                                        WalletRouter router) {
        mInteract = interact;
        mRealmTestDBInteract = realmTestDBInteract;
        walletRouter = router;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new FirstLaunchViewModule(mInteract, mRealmTestDBInteract,walletRouter);
    }
}
