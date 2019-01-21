package com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.Fragment.Account;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletActivityContract;
import com.alphawizard.hdwallet.alphahdwallet.functionModule.Wallet.WalletViewModule;
import com.alphawizard.hdwallet.common.presenter.BaseContract;

public interface AccountContract {

//     adapter 的泛型应该是 交易记录 ，而不是wallet  由于交易记录后面搞， 所以先放放。
    interface View extends BaseContract.BaseRecyclerView<AccountContract.Presenter,WalletViewModule,Wallet> {

    }

    interface Presenter extends BaseContract.BasePresenter<AccountContract.View,WalletViewModule> {
        void getDefaultWallet();
        void getBalance();
    }
}