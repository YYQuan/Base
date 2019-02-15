package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.ViewModule.WalletsViewModuleFactory;


import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.AccountFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.AccountsFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.DimensionFragment;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.LaunchPresenter;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.PersenterInterface;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment.Presenter;
import com.alphawizard.hdwallet.common.base.App.ToolbarActivity;
import com.alphawizard.hdwallet.common.util.Helper.NavHelper;
import com.alphawizard.hdwallet.common.util.MyLogger;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = "/test/activity/wallet")
public class WalletActivity extends ToolbarActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavHelper.OnMenuSelector<Integer>{

    @Inject
    WalletsViewModuleFactory viewModuleFactory;
    WalletViewModule viewModel;

    @Inject
    AccountFragment  accountFragment;

//    @Inject
    LaunchPresenter  launchPresenter;

    @Inject
    PersenterInterface Presenter;


    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private NavHelper<Integer> mHelper;



    public static void show(Context context) {
        context.startActivity(new Intent(context, WalletActivity.class));
    }

    @Override
    public int getContentLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();

        Menu menu = navigation.getMenu();
        menu.performIdentifierAction(R.id.action_wallet,0);

        viewModel = ViewModelProviders.of(this, viewModuleFactory)
                .get(WalletViewModule.class);
//        viewModel.createdWallet().observe(this,this::onCreatedWallet);

        if(accountFragment!=null){
            MyLogger.jLog().d("accountFragment!=null");
        }else{
            MyLogger.jLog().d("accountFragment==null");
        }

        if(Presenter!=null){
            MyLogger.jLog().d("Presenter!=null");
        }else{
            MyLogger.jLog().d("Presenter==null");
        }
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


        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().post("hello");
        EventBus.getDefault().postSticky("hello");
        EventBus.getDefault().postSticky(new Wallet("hello"));
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
