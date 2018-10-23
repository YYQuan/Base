package com.alphawizard.hdwallet.alphahdwallet.di;


import com.alphawizard.hdwallet.alphahdwallet.data.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.data.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchViewModule;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.DefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.FindDefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.GetBalanceInteract;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModuleModule {

    @Provides
    WalletsViewModuleFactory providesWalletsViewModuleFactory(CreateWalletInteract createWalletInteract,
                                                              DefaultWalletInteract defaultWalletInteract,
                                                              FindDefaultWalletInteract findDefaultWalletInteract,
                                                              GetBalanceInteract getBalanceInteract,
                                                              WalletRepositoryType walletRepositoryType){
        return  new WalletsViewModuleFactory(createWalletInteract,defaultWalletInteract,findDefaultWalletInteract,getBalanceInteract,walletRepositoryType);
    }

    @Provides
    FirstLaunchViewModuleFactory providesFirstLaunchViewModule(CreateWalletInteract interact, WalletRouter router){
        return  new FirstLaunchViewModuleFactory(interact,router);
    }


}
