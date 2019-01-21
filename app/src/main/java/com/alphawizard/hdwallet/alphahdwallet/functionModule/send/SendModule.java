package com.alphawizard.hdwallet.alphahdwallet.functionModule.send;

import com.alphawizard.hdwallet.alphahdwallet.di.Scope.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SendModule  {

    @ActivityScoped
    @Binds
    abstract SendContract.Presenter sendPresenter(SendPresenter presenter);
}
