package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet;


import com.alphawizard.hdwallet.alphahdwallet.di.base.Scope.FragmentScoped;

import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.AccountFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.AccountsFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.DimensionFragment;

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
