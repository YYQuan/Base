package com.alphawizard.hdwallet.common.base.Layout.PlaceHolder;

import android.support.annotation.StringRes;

/**
 * Created by Yqquan on 2018/7/23.
 */

public interface PlaceHolderView {

    /**
     * 数据加载成功， 隐藏占位布局
     */
    void triggerOk();

    /**
     * 数据加载失败 ，显示错误
     */
    void triggerError(@StringRes int resId);

    /**
     * 暂无数据，显示占位布局
     */
    void triggerEmpty();

    void triggerOkOrEmpty(boolean isOk);

    /**
     * 显示读取中
     */
    void triggerLoading();

}
