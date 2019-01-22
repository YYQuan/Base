package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import com.alphawizard.hdwallet.alphahdwallet.di.Scope.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.di.Scope.FragmentScoped;

import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.Fragment.AccountFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.Fragment.AccountsFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.Fragment.DimensionFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link }.
 */
@Module
public abstract class WalletModule {






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
