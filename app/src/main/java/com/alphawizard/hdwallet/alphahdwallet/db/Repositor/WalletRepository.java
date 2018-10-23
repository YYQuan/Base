package com.alphawizard.hdwallet.alphahdwallet.db.Repositor;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;

import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class WalletRepository implements WalletRepositoryType {

	private final OkHttpClient httpClient  = new OkHttpClient();

//    public WalletRepository( OkHttpClient okHttpClient) {
//	    this.httpClient = okHttpClient;
//	}


	public WalletRepository() {
	}

	//	查询账号余额
	@Override
	public Single<BigInteger> balanceInWei(Wallet wallet) {
		return Single.fromCallable(() -> Web3jFactory
					.build(new HttpService("https://rinkeby.infura.io/llyrtzQ3YhkdESt2Fzrk", httpClient, false))
					.ethGetBalance(wallet.address, DefaultBlockParameterName.LATEST)
					.send()
					.getBalance())
                .subscribeOn(Schedulers.io());
	}


}