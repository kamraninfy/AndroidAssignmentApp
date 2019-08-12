package com.androidassignmentapp.view.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.androidassignmentapp.R;
import com.androidassignmentapp.databinding.ActivityAboutCanadaBinding;
import com.androidassignmentapp.utils.Constant;
import com.androidassignmentapp.view.adapter.AboutCanadaAdapter;
import com.androidassignmentapp.viewModel.AboutCanadaViewModel;

import java.util.Observable;
import java.util.Observer;

public class AboutCanadaActivity extends AppCompatActivity implements Observer {

    private ActivityAboutCanadaBinding activityAboutCanadaBinding;
    private AboutCanadaViewModel aboutCanadaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(activityAboutCanadaBinding.toolbar);
        setUpListOfUsersView(activityAboutCanadaBinding.listUser);
        setUpObserver(aboutCanadaViewModel);
    }

    private void initDataBinding() {
        activityAboutCanadaBinding = DataBindingUtil.setContentView(this, R.layout.activity_about_canada);
        aboutCanadaViewModel = new AboutCanadaViewModel(this);
        activityAboutCanadaBinding.setAboutCanadaViewModel(aboutCanadaViewModel);
    }

    // set up the list of user with recycler view
    private void setUpListOfUsersView(RecyclerView listUser) {
        AboutCanadaAdapter aboutCanadaAdapter = new AboutCanadaAdapter();
        listUser.setAdapter(aboutCanadaAdapter);
        listUser.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof AboutCanadaViewModel) {
            AboutCanadaAdapter aboutCanadaAdapter = (AboutCanadaAdapter) activityAboutCanadaBinding.listUser.getAdapter();
            AboutCanadaViewModel aboutCanadaViewModel = (AboutCanadaViewModel) o;
            aboutCanadaAdapter.setUserList(aboutCanadaViewModel.getUserList());
        }
    }

    private void startActivityActionView() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PROJECT_URL)));
    }



    @Override protected void onDestroy() {
        super.onDestroy();
        aboutCanadaViewModel.reset();
    }

}
