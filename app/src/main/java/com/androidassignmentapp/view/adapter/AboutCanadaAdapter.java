package com.androidassignmentapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.androidassignmentapp.R;
import com.androidassignmentapp.databinding.ItemCanadaBinding;
import com.androidassignmentapp.model.CountryFactsModels;
import com.androidassignmentapp.model.Row;
import com.androidassignmentapp.viewModel.ItemCanadaViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for recyclerview for About Canada Activity
 */

public class AboutCanadaAdapter extends RecyclerView.Adapter<AboutCanadaAdapter.UserAdapterViewHolder> {

    private List<Row> userList;

    public AboutCanadaAdapter() {
        this.userList = Collections.emptyList();
    }

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemCanadaBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_canada_, parent, false);


        return new UserAdapterViewHolder(itemUserBinding);
    }


    private void hideData(int position, UserAdapterViewHolder holder) {
        Row row = userList.get(position);

        if (row.getTitle() == null && row.getDescription() == null && row.getImageHref() == null) {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {

        //Sets Data
        holder.bindUser(userList.get(position));

        //hideData(position, holder);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<Row> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemCanadaBinding mItemUserBinding;

        public UserAdapterViewHolder(ItemCanadaBinding itemUserBinding) {
            super(itemUserBinding.itemPeople);
            this.mItemUserBinding = itemUserBinding;
        }

        void bindUser(Row user) {
            if (mItemUserBinding.getAboutCanadaViewModel() == null) {
                mItemUserBinding.setAboutCanadaViewModel(new ItemCanadaViewModel(user, itemView.getContext()));
            } else {
                mItemUserBinding.getAboutCanadaViewModel().setUser(user);
            }
        }
    }
}
