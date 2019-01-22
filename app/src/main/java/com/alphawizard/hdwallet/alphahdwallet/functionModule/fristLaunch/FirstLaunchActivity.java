package com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Button;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.common.base.App.ToolbarActivity;
import com.alphawizard.hdwallet.common.util.Log;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FirstLaunchActivity extends ToolbarActivity {

    @BindView(R.id.btn_create_account)
    Button btnCreate;

    @BindView(R.id.btn_import_account)
    Button btnImportAccount;

    @Inject
    FirstLaunchViewModuleFactory walletsViewModuleFactory;
    FirstLaunchViewModule viewModel;


    @Override
    public int getContentLayoutID() {
        return R.layout.activity_first_launch;
    }




    @Override
    public void initData() {
        super.initData();

        viewModel = ViewModelProviders.of(this, walletsViewModuleFactory)
                .get(FirstLaunchViewModule.class);


        viewModel.createdWallet().observe(this,this::onCreatedWallet);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_create_account)
    void onClickBtnCreate(){
        viewModel.newWallet();
    }

    @OnClick(R.id.btn_import_account)
    void onClickBtnImport(){

    }


    public void onCreatedWallet(Wallet wallet) {
        Log.d("onCreatedWallet");
        viewModel.openWallet(this);
    }
}
