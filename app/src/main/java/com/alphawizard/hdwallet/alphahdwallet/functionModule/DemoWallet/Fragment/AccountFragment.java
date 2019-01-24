package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.widget.Button;
import android.widget.TextView;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletViewModule;
import com.alphawizard.hdwallet.common.base.App.Fragment;
import com.alphawizard.hdwallet.common.util.MyLogger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountFragment extends Fragment {



    @Inject
    WalletsViewModuleFactory viewModuleFactory;
    WalletViewModule viewModel;

    @BindView(R.id.tv_balances)
    TextView mBalance;

    @BindView(R.id.btn_send)
    Button  mSend;

    @OnClick(R.id.btn_send)
    void clickBtnSend(){
    }

    @Override
    public int getContentLayoutID() {
        return  R.layout.fragment_wallet_account;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel = ViewModelProviders.of(this, viewModuleFactory)
                .get(WalletViewModule.class);

        viewModel.defaultWallet().observe(this,this::defaultWalletBalanceChange);
        viewModel.defaultWalletBalance().observe(this,this::defaultWalletBalanceChange);
    }

    private void defaultWalletBalanceChange(Wallet wallet) {

    }

    private void defaultWalletBalanceChange(String s) {
        MyLogger.jLog() .d(" balance : "+s);
        mBalance.setText(s);
    }


}
