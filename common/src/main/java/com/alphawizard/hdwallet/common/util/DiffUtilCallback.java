package com.alphawizard.hdwallet.common.util;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by Yqquan on 2018/7/25.
 */

public class DiffUtilCallback<T extends DiffUtilCallback.DiffRule<T>> extends DiffUtil.Callback {
    private List<T> oldList;
    private List<T> newList;


    public DiffUtilCallback(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);
        return oldItem.isSame(newItem);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);
        return oldItem.isChange(newItem);
    }


    public interface DiffRule<T>{
        boolean isSame(T t);
        boolean isChange(T t);
    }
}
