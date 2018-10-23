package com.alphawizard.hdwallet.alphahdwallet.data.ViewModule;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletViewModule;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.DefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.FindDefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.GetBalanceInteract;

public class WalletsViewModuleFactory implements ViewModelProvider.Factory {


    private CreateWalletInteract createWalletInteract;
    private DefaultWalletInteract defaultWalletInteract;
    private FindDefaultWalletInteract findDefaultWalletInteract;
    private GetBalanceInteract getBalanceInteract;
    private WalletRepositoryType walletRepositoryType;

    public WalletsViewModuleFactory(CreateWalletInteract createWalletInteract,
                                    DefaultWalletInteract defaultWalletInteract,
                                    FindDefaultWalletInteract findDefaultWalletInteract,
                                    GetBalanceInteract getBalanceInteract,
                                    WalletRepositoryType walletRepositoryType) {
        this.createWalletInteract = createWalletInteract;
        this.defaultWalletInteract = defaultWalletInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.getBalanceInteract = getBalanceInteract;
        this.walletRepositoryType =  walletRepositoryType;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new WalletViewModule(createWalletInteract,defaultWalletInteract,findDefaultWalletInteract,getBalanceInteract,walletRepositoryType);
    }
}
