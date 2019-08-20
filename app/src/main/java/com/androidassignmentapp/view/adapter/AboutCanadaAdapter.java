package com.androidassignmentapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.androidassignmentapp.R;
import com.androidassignmentapp.databinding.ItemCanadaBinding;
import com.androidassignmentapp.model.Row;
import com.androidassignmentapp.viewModel.ItemCanadaViewModel;

import java.util.Collections;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Adapter for recyclerview for About Canada Activity
 */

public class AboutCanadaAdapter extends RecyclerView.Adapter<AboutCanadaAdapter.UserAdapterViewHolder> {

    private List<Row> userList;

    public AboutCanadaAdapter() {
        this.userList = Collections.emptyList();
    }

    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,@NonNull int viewType) {

        ItemCanadaBinding itemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_canada_, parent, false);
        return new UserAdapterViewHolder(itemUserBinding);
    }


    @NonNull
    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder,@NonNull int position) {
        //Sets Data
        holder.bindUser(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<Row> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

     static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemCanadaBinding mItemUserBinding;

        private UserAdapterViewHolder(ItemCanadaBinding itemUserBinding) {
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
