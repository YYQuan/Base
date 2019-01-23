package com.alphawizard.hdwallet.alphahdwallet.interact;


import com.alphawizard.hdwallet.alphahdwallet.service.DemoService;
import com.alphawizard.hdwallet.alphahdwallet.service.DemoServiceInterface;

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
