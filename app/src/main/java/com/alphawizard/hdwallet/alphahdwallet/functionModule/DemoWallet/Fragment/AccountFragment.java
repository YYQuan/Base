package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import com.alphawizard.hdwallet.alphahdwallet.App;
import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Permission;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletViewModule;
import com.alphawizard.hdwallet.common.base.App.Fragment;
import com.alphawizard.hdwallet.common.util.MyLogger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;

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
        method1();
    }

    private void defaultWalletBalanceChange(Wallet wallet) {

    }

    private void defaultWalletBalanceChange(String s) {
        MyLogger.jLog() .d(" balance : "+s);
        mBalance.setText(s);
    }

    private static final int RC = 0x0100;

    @Permission(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA},requestCode = RC)
    @AfterPermissionGranted(RC)
//    当有权限时 才会执行
    private void method1() {
//        MyLogger.jLog().e("AfterPermissionGranted  100");
        App.showToast("callback    request perm");
        MyLogger.jLog().e("AfterPermissionGranted  10000000");
    }


//    @AfterPermissionGranted(RC)
    void  requestPerm(){
        App.showToast("callback    request perm");
        MyLogger.jLog().e("AfterPermissionGranted  10000000");

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
