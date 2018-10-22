package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.di.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link }.
 */
@Module
public abstract class WalletModule {

    @ActivityScoped
    @Binds abstract WalletContract.Presenter taskPresenter(WalletPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccountFragment accountFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccountsFragment accountsFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DimensionFragment dimensionFragment();
}
