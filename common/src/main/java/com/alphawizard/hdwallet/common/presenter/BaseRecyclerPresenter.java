package com.alphawizard.hdwallet.common.presenter;

import android.support.v7.util.DiffUtil;

import com.alphawizard.hdwallet.common.base.ViewModule.BaseViewModel;
import com.alphawizard.hdwallet.common.base.widget.RecyclerView.RecyclerAdapter;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.List;



/**
 * Created by Yqquan on 2018/7/31.
 */

public class BaseRecyclerPresenter<Data,ViewModule extends BaseViewModel,V extends BaseContract.BaseRecyclerView> extends BasePresenter<V,ViewModule> {

    public BaseRecyclerPresenter() {

}

    public void refreshData(final List<Data> list){
//         以后 用RX来替换
        Run.onUiAsync(new Action(){
            @Override
            public void call() {
                if(getView()!=null) {
                    getView().getRecyclerViewAdapter().replace(list);
                    getView().onRecyclerChange();
                }
            }
        });

    }

    public void refreshData(final DiffUtil.DiffResult  result ,final List<Data> list){
//          后面用RX来替换
          Run.onUiAsync(new Action() {
            @Override
            public void call() {
                if(getView()==null){
                       return;
                }
                RecyclerAdapter adapter = getView().getRecyclerViewAdapter();
                adapter.getDataList().clear();
                adapter.getDataList().addAll(list);

                getView().onRecyclerChange();

                result.dispatchUpdatesTo(adapter);
            }
        });
    }
}
