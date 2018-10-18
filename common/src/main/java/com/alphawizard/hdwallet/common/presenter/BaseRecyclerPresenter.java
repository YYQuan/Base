package com.alphawizard.hdwallet.common.presenter;

import android.support.v7.util.DiffUtil;
import java.util.List;



/**
 * Created by Yqquan on 2018/7/31.
 */

public class BaseRecyclerPresenter<Data,V extends BaseContract.BaseRecyclerView> extends BasePresenter<V> {

    public BaseRecyclerPresenter() {

}

    public void refreshData(List<Data> list){
//          用RX来替换
//        Run.onUiAsync(new Action(){
//            @Override
//            public void call() {
//                if(getView()!=null) {
//                    getView().getRecyclerViewAdapter().replace(list);
//                    getView().onRecyclerChange();
//                }
//            }
//        });

    }

    public void refreshData(DiffUtil.DiffResult  result ,List<Data> list){
//          用RX来替换
//          Run.onUiAsync(new Action() {
//            @Override
//            public void call() {
//                if(getView()==null){
//                       return;
//                }
//                RecyclerAdapter  adapter = getView().getRecyclerViewAdapter();
//                adapter.getDataList().clear();
//                adapter.getDataList().addAll(list);
//
//                getView().onRecyclerChange();
//
//                result.dispatchUpdatesTo(adapter);
//            }
//        });
    }
}
