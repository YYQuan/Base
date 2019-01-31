package com.alphawizard.hdwallet.alphahdwallet.di.dagger.base;




import com.alphawizard.hdwallet.alphahdwallet.di.dagger.base.Scope.ActivityScoped;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletActivity;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletModule;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch.FirstLaunchActivity;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch.FirstLaunchModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 *
 *  用来 绑定   activity  和 其对应的module
 */
@Module
public abstract class ActivityBindingModule {



    @ActivityScoped
    @ContributesAndroidInjector(modules = FirstLaunchModule.class)
    abstract FirstLaunchActivity firstLaunchActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = WalletModule.class)
    abstract WalletActivity walletActivity();




}
