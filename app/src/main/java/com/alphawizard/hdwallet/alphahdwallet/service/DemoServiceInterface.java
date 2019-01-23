package com.alphawizard.hdwallet.alphahdwallet.service;

import com.alphawizard.hdwallet.alphahdwallet.entiry.Wallet;

import io.reactivex.Single;

public interface DemoServiceInterface {
	/**
	 * Create account in keystore
	 * @param password account password
	 * @return new {@link String}
	 */
	Single<String> createAccount(String password);


}
