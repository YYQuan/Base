package com.alphawizard.hdwallet.common.base.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 *
 * 由Adapter 实现， 交给Holder ，让holder能与adapter交互
 * Created by Yqquan on 2018/6/29.
 */

public interface AdapterCallback<VH  extends BaseViewHolder,Data> {
    void update(VH holder, Data data);
}
