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
 * provides      router
 *
 * activity 之间  跳转 模块的提供
 */
@Module
public class RouterModule {



    @Provides
    public static FirstLaunchRouter providesFirstLaunchRouter(){
        return new FirstLaunchRouter();
    }

    @Provides
    public static WalletRouter providesWalletRouter(){
        return new WalletRouter();
    }

    @Provides
    public static SendRouter providesSendRouter(){
        return new SendRouter();
    }

}
