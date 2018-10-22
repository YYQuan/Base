package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import android.arch.lifecycle.ViewModelProviders;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.data.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.common.presenter.BasePresenterFragment;

import javax.inject.Inject;

public class AccountFragment extends BasePresenterFragment<WalletContract.Presenter,WalletViewModule> {

    @Inject
    WalletContract.Presenter mPresenter;

    @Inject
    WalletsViewModuleFactory viewModuleFactory;
    WalletViewModule viewModel;


    @Override
    public void initData() {
        super.initData();
        viewModel = ViewModelProviders.of(this, viewModuleFactory)
                .get(WalletViewModule.class);
    }

    @Override
    public WalletContract.Presenter initPresenter() {
        return mPresenter;
    }

    @Override
    public WalletViewModule initViewModule() {
        return viewModel;
    }

    @Override
    public int getContentLayoutID() {
        return  R.layout.fragment_wallet_account;
    }
}
