package com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch;

import android.os.Bundle;
import android.app.Activity;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.alphahdwallet.service.AccountKeystoreService;
import com.alphawizard.hdwallet.common.presenter.BaseContract;
import com.alphawizard.hdwallet.common.presenter.BasePresenterToolbarActivity;
import com.alphawizard.hdwallet.common.util.Log;


import javax.inject.Inject;

public class FirstLaunchActivity extends BasePresenterToolbarActivity<FirstLaunchContract.Presenter> implements  FirstLaunchContract.View {

    @Inject
    FirstLaunchContract.Presenter  mPresenter;

    @Inject
    Test test;

    @Inject
    String taskId;
//    @Inject
    AccountKeystoreService keystoreService;

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

        if(test!=null){
            Log.d("test :"+test.toString());
        }else{
            Log.d("test :  null");
        }

        if(mPresenter!=null){
            Log.d("mPresenter :"+mPresenter.toString());
        }else{
            Log.d("mPresenter :  null");
        }

        if(keystoreService!=null){
            Log.d("keystoreService :"+keystoreService.toString());
        }else{
            Log.d("keystoreService :  null");
        }
    }
}
