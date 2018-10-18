package com.alphawizard.hdwallet.common.base.widget.RecyclerView;

/**
 *
 * 由Adapter 实现， 交给Holder ，让holder能与adapter交互
 * Created by Yqquan on 2018/6/29.
 */

public interface AdapterCallback<Data> {
    void update(RecyclerAdapter.ViewHolder holder, Data data);
}
