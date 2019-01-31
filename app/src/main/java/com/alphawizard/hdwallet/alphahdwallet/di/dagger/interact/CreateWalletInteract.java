package com.alphawizard.hdwallet.alphahdwallet.di.dagger.interact;


import com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.service.DemoServiceInterface;

import io.reactivex.Single;


public class CreateWalletInteract {

	DemoServiceInterface demoService;

	public CreateWalletInteract(DemoServiceInterface demoService) {
		this.demoService =  demoService;
	}

	public Single<String> create() {
		return demoService.createAccount("");
	}

}
