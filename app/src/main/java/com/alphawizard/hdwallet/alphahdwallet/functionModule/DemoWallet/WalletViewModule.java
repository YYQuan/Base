package com.alphawizard.hdwallet.alphahdwallet.functionModule.DemoWallet;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.alphawizard.hdwallet.alphahdwallet.entity.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.CreateWalletInteract;
import com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact.DefaultWalletInteract;
import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;

public class WalletViewModule extends BaseViewModel {

    private static final long GET_BALANCE_INTERVAL = 8;

    CreateWalletInteract mCreateWalletInteract;
    DefaultWalletInteract mDefaultWalletInteract;

    private final MutableLiveData<Wallet[]> wallets = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<Wallet> createdWallet = new MutableLiveData<>();


    private final MutableLiveData<String> defaultWalletBalance = new MutableLiveData<>();

    public WalletViewModule(CreateWalletInteract createWalletInteract,
                            DefaultWalletInteract defaultWalletInteract)
    {
        mCreateWalletInteract = createWalletInteract;
        mDefaultWalletInteract = defaultWalletInteract;

    }

    public LiveData<Wallet[]> wallets() {
        return wallets;
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }

    public LiveData<Wallet> createdWallet() {
        return createdWallet;
    }

    public LiveData<String> defaultWalletBalance() {
        return defaultWalletBalance;
    }

    public void newWallet() {
        progress.setValue(true);
        mCreateWalletInteract
                .create()
//				create 过程中没有throw 就回调success ,如果有throw  异常的话，那么就会掉error
                .subscribe(account -> {
//                    fetchWallets();
                    createdWallet.postValue(new Wallet(account));
                });
    }


}
