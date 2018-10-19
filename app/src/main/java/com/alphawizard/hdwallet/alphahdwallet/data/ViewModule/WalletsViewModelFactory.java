package com.alphawizard.hdwallet.alphahdwallet.data.ViewModule;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class WalletsViewModelFactory implements ViewModelProvider.Factory {



    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new WalletViewModel();
    }
}
