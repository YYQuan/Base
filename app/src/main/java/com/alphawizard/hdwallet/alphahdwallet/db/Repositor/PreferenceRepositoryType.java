package com.alphawizard.hdwallet.alphahdwallet.db.Repositor;



public interface PreferenceRepositoryType {
	String getCurrentWalletAddress();
	void setCurrentWalletAddress(String address);

	String getDefaultNetwork();
	void setDefaultNetwork(String netName);


}
