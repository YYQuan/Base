package com.alphawizard.hdwallet.common.base.App;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.alphawizard.hdwallet.common.base.Layout.PlaceHolder.PlaceHolderView;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 *
 * 基类Fragment
 * Created by Yqquan on 2018/6/29.
 */

public abstract class Fragment extends dagger.android.support.DaggerFragment {
    protected Unbinder mUnbinder;
    protected ImmersionBar mImmersionBar;
    protected View   mRoot;
    protected Activity mActivity;

    private  boolean isFirst  = true ;

    public PlaceHolderView mPlaceHolderView;
    /**
     *
     * @return  返回一个布局ID
     */
    @LayoutRes
    public abstract  int getContentLayoutID();



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = ((Activity) context);
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mImmersionBar == null)
            mImmersionBar = ImmersionBar.with(mActivity);
        super.onCreateView(inflater, container, savedInstanceState);

        if(mRoot==null) {
            mRoot = inflater.inflate(getContentLayoutID(), container, false);
            mUnbinder = ButterKnife.bind(this,mRoot);
            initWidget(mRoot);

        }else{
            mUnbinder = ButterKnife.bind(this,mRoot);
            if(mRoot.getParent()!=null){
                ((ViewGroup)(mRoot.getParent())).removeView(mRoot);
            }
        }

        return mRoot;
    }

    /**
     * 第一次 初始化的Fragment才会被调用
     */
    public void initFirst() {
        //        EventBus.getDefault().register(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(isFirst){
            isFirst = false;
            initFirst();
        }
        initData();
    }

    /**
     * 已经绑定了ButterKnife
     * @param view
     */
    public  void initWidget(View view){

    }


    /**
     * 在view被fragment加载成功后，才调用；
     * 用于初始化数据
     */
    public void initData() {

    }

    /**
     * 在attach时就被调用
     * @param arguments
     */
    public void initArgs(Bundle arguments) {
    }

    /**
     * 意图： backPressed时的反馈
     * @return  false: 不拦截backPressed事件 ，交给关联的 activity处理  ；true： 消耗backPressed事件，activity不用处理了
     */
    public  boolean  onBackPressed(){
        return false;
    }

    public void setPlaceHolderView(PlaceHolderView mPlaceHolderView) {
        this.mPlaceHolderView = mPlaceHolderView;
    }

    protected void initImmersionBar() {
        if (mImmersionBar != null) mImmersionBar.init();
    }


    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelAll();

        mUnbinder.unbind();

        if (mImmersionBar != null){
            mImmersionBar.destroy();
        }
        //        EventBus.getDefault().register(this);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();

    }
}
