package com.alphawizard.hdwallet.common.presenter;


import com.alphawizard.hdwallet.common.base.widget.RecyclerView.RecyclerAdapter;

/**
 * Created by Yqquan on 2018/7/13.
 *
 * presenter  , fragment继承和实现的都是  接口
 * Fragment的initPresenter 创建的是实体类；
 * 由于是接口因此在fragment中调用getmPresenter（）.getView（）是找不到和presenter bind 的view的
 * 需要对getmPresenter进行强转，为实体类才能够调用到getView();
 * 因为getVIew不是在接口下实现的，而是在实现了BaseContract.BasePresenter的实体类中创建的
 */

public interface BaseContract {

    interface BaseView <T extends BasePresenter>{
        void  showError(int errorCode);
//        理解为初始化就好，不一定是load
        void  showLoading();
        void  setPresenter(T presenter);
    }

    interface BaseRecyclerView <P extends BasePresenter,T> extends BaseView<P>{
        RecyclerAdapter<T> getRecyclerViewAdapter();
        void onRecyclerChange();
    }

    interface BasePresenter<V>{
        /**
         * Binds presenter with a view when resumed. The Presenter will perform initialization here.
         *
         * @param view the view associated with this presenter
         */
        void takeView(V view);

        /**
         * Drops the reference to the view when destroyed
         */
        void dropView();

        V  getView();

        void create();
        void start();
        void destroy();
    }


}
