package com.alphawizard.hdwallet.alphahdwallet.service;



import com.alphawizard.hdwallet.alphahdwallet.entiry.Wallet;

import io.reactivex.Single;


public class DemoService implements DemoServiceInterface {

    @Override
    public Single<String> createAccount(String password) {
        return Single.fromCallable(()->"demo:  hello  world");
    }
}
