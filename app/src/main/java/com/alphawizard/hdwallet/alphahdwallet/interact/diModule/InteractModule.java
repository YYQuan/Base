package com.alphawizard.hdwallet.alphahdwallet.interact.diModule;


import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletRouter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchRouter;
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
 * provides  interact and     router
 *   功能模块的提供
 *
 */
@Module
public class InteractModule {

    @Provides
    static CreateWalletInteract createWalletInteract(WalletRepositoryType walletRepositoryType ){
        return new CreateWalletInteract(walletRepositoryType);
    }

    @Provides
    static DefaultWalletInteract defaultWalletInteract(WalletRepositoryType walletRepositoryType, PreferenceRepositoryType  preferenceRepositoryType){
        return new DefaultWalletInteract(walletRepositoryType,preferenceRepositoryType);
    }

    @Provides
    static FindDefaultWalletInteract findDefaultWalletInteract(PreferenceRepositoryType  preferenceRepositoryType, WalletRepositoryType walletRepositoryType){
        return new FindDefaultWalletInteract(preferenceRepositoryType,walletRepositoryType);
    }

    @Provides
    static GetBalanceInteract getBalanceInteract(WalletRepositoryType walletRepositoryType){
        return new GetBalanceInteract(walletRepositoryType);
    }


    @Provides
    static SendTransactionInteract sendTransactionInteract(WalletRepositoryType walletRepositoryType){
        return new SendTransactionInteract(walletRepositoryType);
    }

    @Provides
    static FetchWalletInteract fetchWalletInteract(WalletRepositoryType walletRepositoryType){
        return new FetchWalletInteract(walletRepositoryType);
    }
//
//    @Provides
//    public static FirstLaunchRouter providesFirstLaunchRouter(){
//        return new FirstLaunchRouter();
//    }
//
//    @Provides
//    public static WalletRouter providesWalletRouter(){
//        return new WalletRouter();
//    }
//
//    @Provides
//    public static SendRouter providesSendRouter(){
//        return new SendRouter();
//    }

}
