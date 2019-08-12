package com.androidassignmentapp.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;


import com.androidassignmentapp.R;
import com.androidassignmentapp.app.AppController;
import com.androidassignmentapp.model.CountryFactsModels;
import com.androidassignmentapp.model.Row;
import com.androidassignmentapp.network.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static com.androidassignmentapp.utils.Constant.RANDOM_USER_URL;

/**
 */

public class AboutCanadaViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt userLabel;

    public ObservableField<String> messageLabel;

    public ObservableField<String> messageheader;

    private List<Row> userList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AboutCanadaViewModel(@NonNull Context context) {
        this.context = context;
        this.userList = new ArrayList<>();

        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        userLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_message_loading_users));
        messageheader = new ObservableField<>(context.getString(R.string.app_name));
        ;


        if (isConnected()) {
            initializeViews();
            fetchUsersList();
        } else {
            messageLabel.set(context.getString(R.string.error_message_loading_internet));
            progressBar.set(View.GONE);
            userLabel.set(View.VISIBLE);
            userRecycler.set(View.GONE);
        }

    }

    /*public void onClickFabToLoad(View view) {
        initializeViews();
        fetchUsersList();
    }*/

    //It is "public" to show an example of test
    public void initializeViews() {
        userLabel.set(View.GONE);
        userRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    private void fetchUsersList() {

        AppController appController = AppController.create(context);
        UsersService usersService = appController.getUserService();

        Disposable disposable = usersService.fetchUsers(RANDOM_USER_URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CountryFactsModels>() {
                    @Override
                    public void accept(CountryFactsModels userResponse) throws Exception {


                        Log.e("TITLE", "" + userResponse.getTitle());
                        updateActionBartitle(userResponse.getTitle());

                        updateUserDataList(userResponse.getRows());
                        progressBar.set(View.GONE);
                        userLabel.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {


                        messageLabel.set(context.getString(R.string.error_message_loading_users));
                        progressBar.set(View.GONE);
                        userLabel.set(View.VISIBLE);
                        userRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void updateActionBartitle(String title) {
        messageheader.set(title);
    }

    private void updateUserDataList(List<Row> peoples) {
        userList.addAll(peoples);
        setChanged();
        notifyObservers();
    }


    /**
     * Method to Check Internet
     * @return false if internet is not connected
     */
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }


    public List<Row> getUserList() {
        return userList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
