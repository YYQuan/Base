package com.alphawizard.hdwallet.alphahdwallet.functionModule.fristLaunch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.alphawizard.hdwallet.alphahdwallet.R;
import com.alphawizard.hdwallet.common.presenter.BasePresenterToolbarActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstLaunchActivity extends BasePresenterToolbarActivity<FirstLaunchContract.Presenter> implements FirstLaunchContract.View {

    public static void show(Context context) {
        context.startActivity(new Intent(context, FirstLaunchActivity.class));
    }


    @BindView(R.id.btn_create_account)
    Button btnCreate;

    @BindView(R.id.btn_import_account)
    Button btnImportAccount;

    @Inject
    FirstLaunchContract.Presenter mPresenter;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.btn_create_account)
    void onClickBtnCreate(){
        mPresenter.createWallet();
    }

    @OnClick(R.id.btn_import_account)
    void onClickBtnImport(){

    }
}
