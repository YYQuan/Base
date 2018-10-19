package com.alphawizard.hdwallet.alphahdwallet.interact;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;

import java.security.SecureRandom;

import javax.inject.Inject;

import io.reactivex.Single;


public class CreateWalletInteract {


	@Inject
	AccountKeystoreService service;



	public CreateWalletInteract(){	}

	public Single<Wallet> create() {
		return generatePassword()
		.flatMap(password -> service.createAccount(password));
	}


	//产生 随机  password
	public Single<String> generatePassword() {
		return Single.fromCallable(() -> {
			byte bytes[] = new byte[256];
			SecureRandom random = new SecureRandom();
			random.nextBytes(bytes);
			return new String(bytes);
		});
	}


}
