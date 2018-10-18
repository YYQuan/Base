package com.alphawizard.hdwallet.alphahdwallet.functionModule.main;

import com.alphawizard.hdwallet.alphahdwallet.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * {@link }.
 */
@Module
public abstract class MainModule {


    @ActivityScoped
    @Binds abstract MainContract.Presenter taskPresenter(MainPresenter presenter);
}
