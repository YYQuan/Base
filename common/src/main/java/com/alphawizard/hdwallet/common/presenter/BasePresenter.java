package com.alphawizard.hdwallet.common.presenter;

/**
 * Created by Yqquan on 2018/7/13.
 */

public class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {

    private V  view = null;

    public BasePresenter() {

    }




    @Override
    public void takeView(V view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void dropView() {
        this.view = null;
        view.setPresenter(null);
    }

    @Override
    public V getView() {
        return view;
    }

    @Override
    public void create() {

    }

    @Override
    public void start() {
        if(view !=null){
            view.showLoading();
        }
    }

    @Override
    public void destroy() {
        dropView();
    }

}
