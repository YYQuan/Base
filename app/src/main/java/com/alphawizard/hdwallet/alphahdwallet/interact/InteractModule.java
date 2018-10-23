package com.alphawizard.hdwallet.alphahdwallet.interact;


import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.di.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchRouter;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

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
    static DefaultWalletInteract  defaultWalletInteract(PreferenceRepositoryType  preferenceRepositoryType){
        return new DefaultWalletInteract(preferenceRepositoryType);
    }

    @Provides
    static FindDefaultWalletInteract  findDefaultWalletInteract(AccountKeystoreService service,PreferenceRepositoryType  preferenceRepositoryType){
        return new FindDefaultWalletInteract(service,preferenceRepositoryType);
    }

    @Provides
    static  GetBalanceInteract  getBalanceInteract(WalletRepositoryType walletRepositoryType){
        return new GetBalanceInteract(walletRepositoryType);
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
