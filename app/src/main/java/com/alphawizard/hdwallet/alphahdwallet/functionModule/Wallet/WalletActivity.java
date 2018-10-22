package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.data.ViewModule.FirstLaunchViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.data.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch.FirstLaunchViewModule;


import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;
import com.alphawizard.hdwallet.common.presenter.BaseContract;
import com.alphawizard.hdwallet.common.presenter.BasePresenterToolbarActivity;
import com.alphawizard.hdwallet.common.util.Helper.NavHelper;
import com.alphawizard.hdwallet.common.util.Log;

import javax.inject.Inject;

import butterknife.BindView;

public class WalletActivity extends BasePresenterToolbarActivity<WalletContract.Presenter,WalletViewModule> implements WalletContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavHelper.OnMenuSelector<Integer>{

    @Inject
    WalletContract.Presenter mPresenter;

    @Inject
    WalletsViewModuleFactory viewModuleFactory;
    WalletViewModule viewModel;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private NavHelper<Integer> mHelper;

    private TextView mTextMessage;

    public static void show(Context context) {
        context.startActivity(new Intent(context, WalletActivity.class));
    }

    @Override
    public int getContentLayoutID() {
        return R.layout.activity_main;
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
    public void initData() {
        super.initData();
        Menu menu = navigation.getMenu();
        menu.performIdentifierAction(R.id.action_wallet,0);

        viewModel = ViewModelProviders.of(this, viewModuleFactory)
                .get(WalletViewModule.class);
//        viewModel.createdWallet().observe(this,this::onCreatedWallet);

    }

    @Override
    public void initFirst() {
        super.initData();

    }

    @Override
    public void initWidget() {
        super.initWidget();
        mHelper = new NavHelper<>(this,getSupportFragmentManager(),R.id.lay_container,this);
        mHelper.add(R.id.action_wallet, new NavHelper.Tab<>(AccountFragment.class, R.string.title_wallet))
                .add(R.id.action_receive, new NavHelper.Tab<>(DimensionFragment.class, R.string.title_receiver))
                .add(R.id.action_account, new NavHelper.Tab<>(AccountsFragment.class, R.string.title_accounts));
        mTextMessage = (TextView) findViewById(R.id.message);

        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void showSendFragment() {

    }

    @Override
    public void showRecordFragment() {

    }

    @Override
    public void showReceiveFragment() {

    }

    @Override
    public void setPresenter(WalletContract.Presenter presenter) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return mHelper.performClickMenu(item.getItemId());
    }

    @Override
    public void onMenuSucceed(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {

    }

    @Override
    public void onMenuRefresh(NavHelper.Tab<Integer> newTab) {

    }
}
