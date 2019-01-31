package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment;

import android.arch.lifecycle.ViewModelProviders;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletViewModule;
import com.alphawizard.hdwallet.common.base.App.Fragment;


import javax.inject.Inject;

public class DimensionFragment extends Fragment{



    @Inject
    WalletsViewModuleFactory viewModuleFactory;
    WalletViewModule viewModel;

    @Override
    public int getContentLayoutID() {
        return  R.layout.fragment_wallet_dimension;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel = ViewModelProviders.of(this, viewModuleFactory)
                .get(WalletViewModule.class);
    }
}
