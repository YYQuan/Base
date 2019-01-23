package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.data.adapterBean.WalletAdapter;
import com.alphawizard.hdwallet.alphahdwallet.di.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletViewModule;
import com.alphawizard.hdwallet.common.base.App.Fragment;
import com.alphawizard.hdwallet.common.base.Layout.PlaceHolder.EmptyLayout;

import com.alphawizard.hdwallet.common.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AccountsFragment extends Fragment {



    @Inject
    WalletsViewModuleFactory viewModuleFactory;
    WalletViewModule viewModel;

    @BindView(R.id.place_holder)
    EmptyLayout placeHolder;

    @BindView(R.id.recyclerView_accounts)
    RecyclerView recyclerView;

    WalletAdapter mAdapter;

    private Wallet defaultWallet ;
    List<Wallet>  wallets =  new ArrayList<>();

    @Override
    public int getContentLayoutID() {
        return R.layout.fragment_wallet_accounts;
    }

    @Override
    public void initData() {
        super.initData();
        viewModel = ViewModelProviders.of(this, viewModuleFactory)
                .get(WalletViewModule.class);
        viewModel.wallets().observe(this,this::onGetWallets);
        viewModel.defaultWallet().observe(this,this::onDefaultWallet);

    }

    private void onDefaultWallet(Wallet wallet) {
        Log.d("default wallet  address:" +wallet.address);
        defaultWallet = wallet;
        mAdapter.setDefautlAddress(defaultWallet.address);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAccounts();
        viewModel.getDefaultWallet();
    }

    private void onGetWallets(Wallet[] walletArrays) {
        if(walletArrays.length>0){
            placeHolder.triggerOkOrEmpty(true);
        }
        this.wallets = Arrays.asList(walletArrays);
        mAdapter.refreshData(this.wallets);

    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter =new WalletAdapter(R.layout.cell_accounts_list,wallets);
        mAdapter.setOnItemClickListener((adapter1,view1,positon1)-> {
            viewModel.setDefaultWallet((Wallet)adapter1.getData().get(positon1));
        });
        recyclerView.setAdapter(mAdapter);

        setPlaceHolderView(placeHolder);
        placeHolder.bind(recyclerView);
    }








}
