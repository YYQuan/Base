package com.alphawizard.hdwallet.alphahdwallet.functionModule.send;

import com.alphawizard.hdwallet.alphahdwallet.di.Scope.ActivityScoped;
import com.alphawizard.hdwallet.common.presenter.BasePresenter;

import javax.inject.Inject;

@ActivityScoped
public class SendPresenter extends BasePresenter<SendContract.View,SendViewModule> implements SendContract.Presenter  {
    @Inject
    public SendPresenter() {
    }

    @Override
    public void sendTransaction(String from  ,String  to , String amount) {
        getViewModule().sendTransaction(from ,to ,amount);
    }
}
