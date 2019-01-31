package com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.service;

import io.reactivex.Single;

public interface DemoServiceInterface {
	/**
	 * Create account in keystore
	 * @param password account password
	 * @return new {@link String}
	 */
	Single<String> createAccount(String password);


}
