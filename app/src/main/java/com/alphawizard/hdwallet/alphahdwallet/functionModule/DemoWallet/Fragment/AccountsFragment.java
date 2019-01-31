package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alphawizard.hdwallet.alphahdwallet.App;
import com.alphawizard.hdwallet.alphahdwallet.R;

import com.alphawizard.hdwallet.alphahdwallet.di.dagger.ViewModule.WalletsViewModuleFactory;
import com.alphawizard.hdwallet.alphahdwallet.entity.Bean.AdapterBean.WalletAdapter;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet.WalletViewModule;
import com.alphawizard.hdwallet.common.base.App.Fragment;
import com.alphawizard.hdwallet.common.base.Layout.PlaceHolder.EmptyLayout;
import com.alphawizard.hdwallet.common.util.MyLogger;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AccountsFragment extends Fragment {



    @Inject
    WalletsViewModuleFactory viewModuleFactory;
    WalletViewModule viewModel;

    @BindView(R.id.place_holder)
    EmptyLayout placeHolder;

    @BindView(R.id.recyclerView_accounts)
    RecyclerView recyclerView;

    @OnClick(R.id.btn_test)
    void   clickTest(){
       List<Wallet> walletArrays = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            walletArrays.add(new Wallet(" " + i));
        }
        mAdapter.refreshData(walletArrays);
    }

    WalletAdapter mAdapter;

    private Wallet defaultWallet ;


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
        MyLogger.jLog().d("default wallet  address:" +wallet.address);
        defaultWallet = wallet;
        mAdapter.setDefautlAddress(defaultWallet.address);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void onGetWallets(Wallet[] walletArrays) {
        if(walletArrays.length>0){
            placeHolder.triggerOkOrEmpty(true);
        }
        List<Wallet>  wallets =  Arrays.asList(walletArrays);
        mAdapter.refreshData(wallets);

    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        List<Wallet>  wallets =  new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       for(int i = 10 ;i<30;i++){
           wallets.add(new Wallet(" "+i));
       }

        mAdapter =new WalletAdapter(R.layout.cell_accounts_list,wallets);

//        顶部 刷新开关
//        这个 顶部刷新  和下拉刷新 的效果差很多 ， 要做 下拉刷新的话不能用这个来做 ，
//        还是要通过SwipeRefreshLayout  来做
       mAdapter.setUpFetchEnable(true);

//       下拉刷新
        mAdapter.setUpFetchListener(()->{
            App.showToast("   up   fetch Load  more");
            Single.timer(2, TimeUnit.SECONDS)
                    .fromCallable(()->{
                List<Wallet>  wallets1 =  new ArrayList<>();

                for (int i = 0; i < 30; i++) {
                    wallets1.add(new Wallet(" " + i));
                }
                return  wallets1;
            })
//            初始化  在  subsrcribe 前被调用
                    .doOnSubscribe((params)->{
                        MyLogger.jLog().d("网络访问  初始化");

                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe((param)->{
                        mAdapter.refreshData(param);
//                mAdapter.loadMoreEnd();
                        mAdapter.loadMoreComplete();
                    });

        });

//         上拉  刷新
////            请不要  直接的在 主线中用这个来刷新 ，会 由于刷新太快 而导致出错
       mAdapter.setOnLoadMoreListener(()->{
            App.showToast("Load  more");
           Single.fromCallable(()->{
               List<Wallet>  wallets1 =  new ArrayList<>();

               for (int i = 0; i < mAdapter.getData().size()+10; i++) {
                   wallets1.add(new Wallet(" " + i));
               }
               return  wallets1;
           }).subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())

                   .subscribe((param)->{
                       mAdapter.refreshData(param);
//                mAdapter.loadMoreEnd();
                       mAdapter.loadMoreComplete();
                   });


//            recyclerView.postDelayed(()-> {
//                List<Wallet>  wallets1 =  new ArrayList<>();
//
//                for (int i = 0; i < mAdapter.getData().size()+10; i++) {
//                    wallets1.add(new Wallet(" " + i));
//                }
//                mAdapter.refreshData(wallets1);
////                mAdapter.loadMoreEnd();
//                mAdapter.loadMoreComplete();
//            },1000);
        });
        mAdapter.setOnItemClickListener((adapter1,view1,positon1)-> {
            App.showToast("setOnItemClickListener  ");
            wallets.clear();

            for (int i = 0; i < 30; i++) {
                wallets.add(new Wallet(" " + i));
            }
            mAdapter.refreshData(wallets);
        });

        recyclerView.setAdapter(mAdapter);

        setPlaceHolderView(placeHolder);
        placeHolder.bind(recyclerView);

        placeHolder.triggerOk();

    }








}
