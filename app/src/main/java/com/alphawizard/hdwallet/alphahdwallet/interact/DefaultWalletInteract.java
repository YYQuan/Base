package com.alphawizard.hdwallet.alphahdwallet.interact;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.PreferenceRepositoryType;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;

import io.reactivex.Completable;
import io.reactivex.Single;

public class DefaultWalletInteract {

    PreferenceRepositoryType preferenceRepositoryType;

    public DefaultWalletInteract(PreferenceRepositoryType   preferenceRepositoryType) {
        this.preferenceRepositoryType =   preferenceRepositoryType;
    }


    public Completable setDefaultWallet(Wallet wallet){
        return Completable.fromAction(()->preferenceRepositoryType.setCurrentWalletAddress(wallet.address));
    }


    public Single<Wallet> getDefaultWallet(){
        return null;
    }
}
