package com.androidassignmentapp.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.androidassignmentapp.model.Row;
import com.bumptech.glide.Glide;

/**
 * Row Item Class
 */

public class ItemCanadaViewModel extends BaseObservable {

    private Row user;
    private Context context;

    public ItemCanadaViewModel(Row user, Context context){
        this.user = user;
        this.context = context;
    }

    public String getProfileThumb() {

        if(user.getImageHref()!=null){
            return user.getImageHref().toString();
        }else{
            return "";
        }

    }

    // Loading Image using Glide Library.
    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getTitle() { return user.getTitle(); }

    public String getDescription() { return user.getDescription(); }

    public void onItemClick(View v){
        //context.startActivity(UserDetailActivity.fillDetail(v.getContext(), user));
    }

    public void setUser(Row user) {
        this.user = user;
        notifyChange();
    }
}
