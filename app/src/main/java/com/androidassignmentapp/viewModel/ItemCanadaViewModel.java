package com.androidassignmentapp.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidassignmentapp.model.Row;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Row Item Class
 */

public class ItemCanadaViewModel extends BaseObservable {

    private Row user;
    public ObservableInt noimagevisible;

    private Context context;

    public ItemCanadaViewModel(Row user, Context context) {
        this.user = user;
        this.context = context;
        noimagevisible = new ObservableInt(View.GONE);
    }

    public String getProfileThumb() {
        if (user.getImageHref() != null) {
            noimagevisible.set(View.VISIBLE);
            return user.getImageHref();
        } else {
            noimagevisible.set(View.GONE);
            return "";
        }
    }

    // Loading Image using Glide Library.
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        imageView.setVisibility(View.GONE);
        Glide.with(imageView.getContext()).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                imageView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                imageView.setVisibility(View.VISIBLE);
                return false;
            }
        }).into(imageView);


    }

    public String getTitle() {
        return user.getTitle();
    }

    public String getDescription() {
        return user.getDescription();
    }

    /**
     * On Item Click of Row Item
     */
    public void onItemClick(View v) {
        if (user.getTitle() != null) {
            showToast(user.getTitle());
        }
    }


    /**
     * Method to show toast in activity
     */
    private void showToast(String title) {
        Toast.makeText(context, "" + title, Toast.LENGTH_SHORT).show();
    }

    public void setUser(Row user) {
        this.user = user;
        notifyChange();
    }
}
