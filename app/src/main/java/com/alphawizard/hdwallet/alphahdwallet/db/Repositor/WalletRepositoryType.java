package com.alphawizard.hdwallet.alphahdwallet.db.Repositor;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;


import java.math.BigInteger;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface WalletRepositoryType {


	Single<BigInteger> balanceInWei(Wallet wallet);
}
