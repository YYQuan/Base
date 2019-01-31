package com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.Repositor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.preference.PreferenceManager;

import com.alphawizard.hdwallet.alphahdwallet.App;
import com.alphawizard.hdwallet.alphahdwallet.di.AspectJ.Annotation.Async;
import com.alphawizard.hdwallet.common.util.MyLogger;

import javax.inject.Inject;

import dagger.android.DaggerApplication;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;


public class SharedPreferenceRepository implements PreferenceRepositoryType {

	public static final String CURRENT_ACCOUNT_ADDRESS_KEY = "current_account_address";
	public static final String DEFAULT_NETWORK_NAME_KEY = "default_network_name";
	public static final String GAS_PRICE_KEY  ="gas_price";
	public static final String GAS_LIMIT_KEY  ="gas_limit";
	public static final String GAS_LIMIT_FOR_TOKENS_KEY = "gas_limit_for_tokens";

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


	@Async
	public void putString(String  key,String value) {
		MyLogger.jLog().e("  PrefsInsert  putString");
		 pref.edit().putString(key, value).apply();
	}

	@Async
	public void putBoolean(String  key,boolean value) {
		pref.edit().putBoolean(key, value).apply();
	}

	@Async
	public void putInt(String  key,int value) {
		pref.edit().putInt(key, value).apply();
	}

	@Async
	public void putFloat(String  key,float value) {
		pref.edit().putFloat(key, value).apply();
	}

	@Async
	public void putLong(String  key,long value) {
		pref.edit().putLong(key, value).apply();
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
