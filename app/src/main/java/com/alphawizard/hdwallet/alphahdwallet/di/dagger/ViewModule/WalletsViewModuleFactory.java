package com.alphawizard.hdwallet.alphahdwallet.di.dagger.ViewModule;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.DefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletViewModule;



public class WalletsViewModuleFactory implements ViewModelProvider.Factory {


    private CreateWalletInteract createWalletInteract;
    private DefaultWalletInteract defaultWalletInteract;

    public WalletsViewModuleFactory(CreateWalletInteract createWalletInteract,
                                    DefaultWalletInteract defaultWalletInteract) {
        this.createWalletInteract = createWalletInteract;
        this.defaultWalletInteract = defaultWalletInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new WalletViewModule(createWalletInteract,defaultWalletInteract);
    }
}
