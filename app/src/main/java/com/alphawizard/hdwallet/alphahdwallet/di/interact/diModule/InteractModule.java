package com.alphawizard.hdwallet.alphahdwallet.di.interact.diModule;


import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.di.Repositor.RealmDBOperatorType;
import com.alphawizard.hdwallet.alphahdwallet.di.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.di.interact.DefaultWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.di.interact.RealmTestDBInteract;
import com.alphawizard.hdwallet.alphahdwallet.service.DemoServiceInterface;

import dagger.Module;
import dagger.Provides;

/**
 * provides  interact and     router
 *   功能模块的提供
 *
 */
@Module
public class InteractModule {

    @Provides
    static CreateWalletInteract createWalletInteract(DemoServiceInterface demoService){
        return new CreateWalletInteract(demoService);
    }

    @Provides
    static DefaultWalletInteract defaultWalletInteract( PreferenceRepositoryType  preferenceRepositoryType){
        return new DefaultWalletInteract(preferenceRepositoryType);
    }

//    @Provides
//    static RealmTestDBInteract realmTestDBInteract(RealmDBOperatorType realmDBOperatorType){
//        return new RealmTestDBInteract(realmDBOperatorType);
//    }



}
