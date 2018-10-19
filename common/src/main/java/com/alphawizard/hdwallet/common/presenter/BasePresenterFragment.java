package com.alphawizard.hdwallet.common.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alphawizard.hdwallet.common.base.App.Application;
import com.alphawizard.hdwallet.common.base.App.Fragment;
import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;

/**
 * Created by Yqquan on 2018/7/13.
 */

public abstract class BasePresenterFragment<P extends  BaseContract.BasePresenter,ViewModule extends BaseViewModel> extends Fragment implements BaseContract.BaseView<P,ViewModule> {

    protected P mPresenter;
    protected ViewModule mViewModule;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = initPresenter();
        mViewModule = initViewModule();
        if(mPresenter !=null&&mViewModule!=null) {
            mPresenter.takeView(this,mViewModule);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mPresenter !=null) {
            mPresenter.create();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void initFirst() {
        super.initFirst();
//        mPresenter.start();
    }

    @Override
    public void onDetach() {
        if(mPresenter !=null) {
            mPresenter.destroy();
        }
        super.onDetach();
    }

    @Override
    public void showError(int errorCode) {

        if(mPlaceHolderView==null) {
            Application.showToast(errorCode);
        }else{
            mPlaceHolderView.triggerError(errorCode);
        }
    }

    @Override
    public void showLoading() {
        if(mPlaceHolderView!=null){
            mPlaceHolderView.triggerLoading();
        }else{

        }
    }




    public P getmPresenter() {
        return mPresenter;
    }

//    子类当中要返回一个P的对象，这样这个P的对象就会通过View的 setPresenter设置到属性变量mPresenter当中
    public abstract P initPresenter();

    //    子类当中要返回一个P的对象，这样这个P的对象就会通过View的 setPresenter设置到属性变量mPresenter当中
    public abstract ViewModule initViewModule();

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

}
