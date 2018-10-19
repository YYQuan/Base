package com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;
import com.alphawizard.hdwallet.common.presenter.BaseContract;
import com.alphawizard.hdwallet.common.presenter.BasePresenterToolbarActivity;
import com.alphawizard.hdwallet.common.util.Log;
import com.google.gson.Gson;


import javax.inject.Inject;

import dagger.Provides;

public class FirstLaunchActivity extends BasePresenterToolbarActivity<FirstLaunchContract.Presenter> implements  FirstLaunchContract.View {

    @Inject
    FirstLaunchContract.Presenter  mPresenter;

    @Override
    public int getContentLayoutID() {
        return R.layout.activity_first_launch;
    }

    @Override
    public FirstLaunchContract.Presenter initPresenter() {
        return mPresenter;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
