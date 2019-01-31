package com.alphawizard.hdwallet.alphahdwallet.di.dagger.base;


import com.alphawizard.hdwallet.alphahdwallet.di.dagger.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.ViewModule.WalletsViewModuleFactory;

import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.RealmTestDBInteract;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.DefaultWalletInteract;

import dagger.Module;
import dagger.Provides;

/**
 * 提供各个 Activity 所需的  ViewModule的Factory
 */
@Module
public class ViewModuleModule {


    @Provides
    WalletsViewModuleFactory providesWalletsViewModuleFactory(CreateWalletInteract createWalletInteract,
                                                              DefaultWalletInteract defaultWalletInteract){
        return  new WalletsViewModuleFactory(createWalletInteract,defaultWalletInteract);
    }


    @Provides
    FirstLaunchViewModuleFactory providesFirstLaunchViewModule(CreateWalletInteract interact,
                                                               RealmTestDBInteract realmTestDBInteract,
                                                               WalletRouter router){
//        return  new FirstLaunchViewModuleFactory(interact,router);
        return  new FirstLaunchViewModuleFactory(interact,realmTestDBInteract,router);
    }

}
