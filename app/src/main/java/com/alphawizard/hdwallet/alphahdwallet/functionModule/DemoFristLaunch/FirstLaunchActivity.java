package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoFristLaunch;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.utils.CommonUtil;
import com.alphawizard.hdwallet.common.base.App.ToolbarActivity;
import com.alphawizard.hdwallet.common.util.MyLogger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = "/test/activity/firstLaunch")
public class FirstLaunchActivity extends ToolbarActivity {

    @BindView(R.id.btn_create_account)
    Button btnCreate;

    @BindView(R.id.btn_import_account)
    Button btnImportAccount;

    @Inject
    FirstLaunchViewModuleFactory walletsViewModuleFactory;
    FirstLaunchViewModule viewModel;

    @Autowired
    String  name ;

    @Override
    public int getContentLayoutID() {
        return R.layout.activity_first_launch;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void initData() {
        super.initData();

        viewModel = ViewModelProviders.of(this, walletsViewModuleFactory)
                .get(FirstLaunchViewModule.class);

        viewModel.observeCreatedWallet().observe(this,this::onCreatedWallet);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_create_account)
    void onClickBtnCreate(){
        viewModel.createNewWallet();
    }

    @OnClick(R.id.btn_import_account)
    void onClickBtnImport(){

    }


    public void onCreatedWallet(Wallet wallet) {
        MyLogger.jLog().d("onCreatedWallet");
        viewModel.openWallet(this);
        ARouter.getInstance().build("/test/activity/wallet")
                .navigation();
    }
}