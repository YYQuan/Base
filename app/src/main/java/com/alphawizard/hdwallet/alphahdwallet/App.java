package com.alphawizard.hdwallet.alphahdwallet;






import com.alphawizard.hdwallet.alphahdwallet.di.DaggerAppComponent;
import com.alphawizard.hdwallet.common.base.App.Application;




import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

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

