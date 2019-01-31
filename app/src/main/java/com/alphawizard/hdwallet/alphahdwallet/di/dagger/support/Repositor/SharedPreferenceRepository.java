package com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alphawizard.hdwallet.alphahdwallet.App;
import com.alphawizard.hdwallet.common.util.MyLogger;

import javax.inject.Inject;

import dagger.android.DaggerApplication;
import io.reactivex.Single;


public class SharedPreferenceRepository implements PreferenceRepositoryType {

	private static final String CURRENT_ACCOUNT_ADDRESS_KEY = "current_account_address";
	private static final String DEFAULT_NETWORK_NAME_KEY = "default_network_name";
	private static final String GAS_PRICE_KEY  ="gas_price";
    private static final String GAS_LIMIT_KEY  ="gas_limit";
	private static final String GAS_LIMIT_FOR_TOKENS_KEY = "gas_limit_for_tokens";

	private static  SharedPreferences pref;
	private static  SharedPreferenceRepository instance;



	private  SharedPreferenceRepository(Context context) {
		pref = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public  static  SharedPreferenceRepository   getInstance(){
		if (instance == null) {
			synchronized (SharedPreferenceRepository.class) {
				if (instance == null) {
					System.out.println("i m in new instance!");
					instance =  new SharedPreferenceRepository(App.getInstance());
				}
			}
		}
		return  instance;
	}

	@Override
	public Single<String> getCurrentWalletAddress() {
		return Single.fromCallable(()->pref.getString(CURRENT_ACCOUNT_ADDRESS_KEY, null));
	}

	@Override
	public void setCurrentWalletAddress(String address) {
		pref.edit().putString(CURRENT_ACCOUNT_ADDRESS_KEY, address).apply();
	}

	@Override
	public String getDefaultNetwork() {
		return pref.getString(DEFAULT_NETWORK_NAME_KEY, null);
	}

	@Override
	public void setDefaultNetwork(String netName) {
		pref.edit().putString(DEFAULT_NETWORK_NAME_KEY, netName).apply();
	}


}
