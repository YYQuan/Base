package com.alphawizard.hdwallet.alphahdwallet.interact.diModule;


import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.interact.DefaultWalletInteract;
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



}
