package com.alphawizard.hdwallet.alphahdwallet.interact;

import com.alphawizard.hdwallet.alphahdwallet.data.entiry.Wallet;
import com.alphawizard.hdwallet.alphahdwallet.db.Repositor.WalletRepositoryType;

import io.reactivex.Single;


public class CreateWalletInteract {

	WalletRepositoryType walletRepository;

	public CreateWalletInteract(WalletRepositoryType walletRepository) {
		this.walletRepository =  walletRepository;
	}

	public Single<Wallet> create() {
		return walletRepository.createAccount();
	}

}
