package com.alphawizard.hdwallet.alphahdwallet.interact;


import com.alphawizard.hdwallet.alphahdwallet.di.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchRouter;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;

import dagger.Module;
import dagger.Provides;

/**
 * provides  interact and     router
 */
@Module
public class InteractModule {

    @Provides
    static CreateWalletInteract  createWalletInteract(AccountKeystoreService service){
        return new CreateWalletInteract( service);
    }

    @Provides
    public static FirstLaunchRouter providesFirstLaunchRouter(){
        return new FirstLaunchRouter();
    }


    @Provides
    public static WalletRouter providesWalletRouter(){
        return new WalletRouter();
    }

}
