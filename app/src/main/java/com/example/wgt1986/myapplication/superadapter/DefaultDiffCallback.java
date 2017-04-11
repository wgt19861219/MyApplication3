package com.example.wgt1986.myapplication.superadapter;

import java.util.List;


/**
 * Default callback class used by DiffUtil while calculating the diff between two lists.
 * <p>
 * Created by Cheney on 2016/9/26.
 */
public abstract class DefaultDiffCallback<T>  {
    private List<T> oldList;
    private List<T> newList;

    public DefaultDiffCallback(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    public List<T> getOldList() {
        return oldList;
    }

    public void setOldList(List<T> oldList) {
        this.oldList = oldList;
    }

    public List<T> getNewList() {
        return newList;
    }

    public void setNewList(List<T> newList) {
        this.newList = newList;
    }

    public abstract boolean areItemsTheSame(int oldItemPosition, int newItemPosition);

    public abstract boolean areContentsTheSame(int oldItemPosition, int newItemPosition);

}
