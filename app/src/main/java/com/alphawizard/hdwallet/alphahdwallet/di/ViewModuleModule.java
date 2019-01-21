package com.alphawizard.hdwallet.alphahdwallet.di;


import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.SendViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.send.SendRouter;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.DefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.FetchWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.FindDefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.GetBalanceInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.SendTransactionInteract;

import dagger.Module;
import dagger.Provides;

/**
 * 提供各个 Activity 所需的  ViewModule的Factory
 */
@Module
public class ViewModuleModule {

    @Provides
    WalletsViewModuleFactory providesWalletsViewModuleFactory(CreateWalletInteract createWalletInteract,
                                                              DefaultWalletInteract defaultWalletInteract,
                                                              FindDefaultWalletInteract findDefaultWalletInteract,
                                                              FetchWalletInteract  fetchWalletInteract,
                                                              GetBalanceInteract getBalanceInteract,
                                                              SendRouter sendRouter,
                                                              WalletRepositoryType walletRepositoryType){
        return  new WalletsViewModuleFactory(createWalletInteract,defaultWalletInteract,findDefaultWalletInteract,fetchWalletInteract,getBalanceInteract,sendRouter,walletRepositoryType);
    }

    @Provides
    FirstLaunchViewModuleFactory providesFirstLaunchViewModule(CreateWalletInteract interact, WalletRouter router){
        return  new FirstLaunchViewModuleFactory(interact,router);
    }

    @Provides
    SendViewModuleFactory providesSendViewModule(SendTransactionInteract interact){
        return  new SendViewModuleFactory(interact);
    }

}
