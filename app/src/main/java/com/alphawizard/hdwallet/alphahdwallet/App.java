package com.alphawizard.hdwallet.alphahdwallet;





import android.app.Activity;

import com.alphawizard.hdwallet.alphahdwallet.di.DaggerAppComponent;
import com.alphawizard.hdwallet.common.base.App.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }
}

