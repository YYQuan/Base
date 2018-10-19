package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.di.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.main.MainContract;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.main.MainRouter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link }.
 */
@Module
public abstract class WalletModule {


    @ActivityScoped
    @Binds abstract WalletContract.Presenter taskPresenter(WalletPresenter presenter);

//    @Provides
//    WalletRouter providesWalletRouter(){
//        return new WalletRouter();
//    }
}
