package com.alphawizard.hdwallet.alphahdwallet.interact;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;

import static com.alphawizard.hdwallet.alphahdwallet.utils.BalanceUtils.weiToEth;

public class GetBalanceInteract {

    WalletRepositoryType  mWalletRepositoryType;
    public GetBalanceInteract(WalletRepositoryType walletRepositoryType) {
        mWalletRepositoryType = walletRepositoryType;
    }


    public Single<String>  getBalance(Wallet wallet){
        return mWalletRepositoryType
                .balanceInWei(wallet)
                .flatMap(ethBallance -> {
                     String balances = weiToEth(ethBallance, 5);
                    return Single.just(balances);
                });
    }
}


