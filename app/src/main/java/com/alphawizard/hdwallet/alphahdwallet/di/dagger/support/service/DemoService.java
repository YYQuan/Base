package com.alphawizard.hdwallet.alphahdwallet.di.dagger.support.service;



import io.reactivex.Single;


public class DemoService implements DemoServiceInterface {

    @Override
    public Single<String> createAccount(String password) {
        return Single.fromCallable(()->"demo:  hello  world");
    }
}
