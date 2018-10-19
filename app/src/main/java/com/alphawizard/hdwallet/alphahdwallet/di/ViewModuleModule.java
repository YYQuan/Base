package com.alphawizard.hdwallet.alphahdwallet.di;


import com.alphawizard.hdwallet.alphahdwallet.data.ViewModule.WalletsViewModelFactory;
import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModuleModule {

    @Provides
    WalletsViewModelFactory   providesWalletsViewModuleFactory(){
        return  new WalletsViewModelFactory();
    }


}
