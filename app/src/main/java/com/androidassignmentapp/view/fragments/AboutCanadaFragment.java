package com.androidassignmentapp.view.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidassignmentapp.R;
import com.androidassignmentapp.databinding.FragmentItemBinding;
import com.androidassignmentapp.view.adapter.AboutCanadaAdapter;
import com.androidassignmentapp.viewModel.AboutCanadaViewModel;

import java.util.Observable;
import java.util.Observer;

public class AboutCanadaFragment extends Fragment implements Observer {

    private AboutCanadaViewModel aboutCanadaViewModel;
    Context mContext;
    private FragmentItemBinding fragmentItemBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view = initDataBinding(inflater, container);
        setUpListOfUsersView(fragmentItemBinding.listUser);
        setUpObserver(aboutCanadaViewModel);

        return view;
    }


    /**
     * Method which will initialise the data binding of particular view
     *
     * @param inflater Layout inflater which is passed from OnCreateView in Fragment
     */
    private View initDataBinding(LayoutInflater inflater, ViewGroup container) {

        View view = null;
        if (fragmentItemBinding == null) {
            fragmentItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false);
            view = fragmentItemBinding.getRoot();
            aboutCanadaViewModel = new AboutCanadaViewModel(mContext);
            fragmentItemBinding.setAboutCanadaViewModel(aboutCanadaViewModel);
        }
        return view;
    }

    // set up the list of user with recycler view
    private void setUpListOfUsersView(RecyclerView listUser) {
        AboutCanadaAdapter aboutCanadaAdapter = new AboutCanadaAdapter();
        listUser.setAdapter(aboutCanadaAdapter);
        //listUser.setLayoutManager(new LinearLayoutManager(mContext));

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            listUser.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        } else {
            listUser.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentItemBinding.listUser.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentItemBinding.listUser.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        }


    }

    /**
     * Method which will add observer to observable
     *
     * @param observable Method to add observer on onservable
     */
    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof AboutCanadaViewModel) {
            AboutCanadaAdapter aboutCanadaAdapter = (AboutCanadaAdapter) fragmentItemBinding.listUser.getAdapter();
            AboutCanadaViewModel aboutCanadaViewModel = (AboutCanadaViewModel) o;

            aboutCanadaAdapter.setUserList(aboutCanadaViewModel.getUserList());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        aboutCanadaViewModel.reset();
    }
}