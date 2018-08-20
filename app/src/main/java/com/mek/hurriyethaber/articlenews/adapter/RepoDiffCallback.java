package com.mek.hurriyethaber.articlenews.adapter;

import android.support.v7.util.DiffUtil;

import com.mek.hurriyethaber.articlenews.model.NewsModel;

import java.util.List;

public class RepoDiffCallback extends DiffUtil.Callback {

    private final List<NewsModel> oldList;
    private final List<NewsModel> newList;

    public RepoDiffCallback(List<NewsModel> oldList, List<NewsModel> newList) {
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
        return oldList.get(oldItemPosition).id().equals(newList.get(newItemPosition).id());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
