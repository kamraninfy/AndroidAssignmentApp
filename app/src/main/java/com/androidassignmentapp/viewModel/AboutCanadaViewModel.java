package com.androidassignmentapp.viewModel;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.androidassignmentapp.R;
import com.androidassignmentapp.app.CustomApplication;
import com.androidassignmentapp.database.CountryDatabase;
import com.androidassignmentapp.database.constants.DbConstants;
import com.androidassignmentapp.database.entity.CountryEntity;
import com.androidassignmentapp.database.entity.RowEntity;
import com.androidassignmentapp.database.repository.CountryRepo;
import com.androidassignmentapp.database.repository.CountryRepository;
import com.androidassignmentapp.model.CountryFactsModels;
import com.androidassignmentapp.model.Row;
import com.androidassignmentapp.network.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.androidassignmentapp.utils.Constant.RANDOM_USER_URL;

/**
 * ViewModel for handling logic for About Canada Activity class
 */

public class AboutCanadaViewModel extends Observable {

    private final CountryDatabase mDatabase;
    private CountryRepo countryRepo;
    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt userLabel;
    public ObservableField<String> messageLabel;
    public ObservableField<String> messageheader;
    private List<Row> userList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public ObservableBoolean isLoading;

    public AboutCanadaViewModel(@NonNull Context context) {
        this.context = context;
        this.userList = new ArrayList<>();

        //Database
        mDatabase = Room.databaseBuilder(context, CountryDatabase.class, DbConstants.DB_NAME).build();
        countryRepo = new CountryRepository(mDatabase.countryDao());

        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        userLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_message_loading_users));
        messageheader = new ObservableField<>(context.getString(R.string.app_name));
        isLoading = new ObservableBoolean();

        initializeViews();
        fetchLocalApi();
    }


    /**
     * Method which would fetch data locally if internet is not connected
     */
    @SuppressLint("CheckResult")
    private void fetchLocalApi() {
        countryRepo.getAllCountryInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(countryEntity -> {
            updateActionBartitle(countryEntity.getTitle());

            List<Row> rowList = new ArrayList<>();
            for (int i = 0; i < countryEntity.getRows().size(); i++) {
                Row row = new Row();
                row.setTitle(countryEntity.getRows().get(i).getTitle());
                row.setDescription(countryEntity.getRows().get(i).getDescription());
                row.setImageHref(countryEntity.getRows().get(i).getImageHref());
                rowList.add(row);
            }

            isLoading.set(false);
            updateUserDataList(rowList);
            progressBar.set(View.GONE);
            userLabel.set(View.GONE);
            userRecycler.set(View.VISIBLE);


            //This would call API in background if internet is connected
            if (isConnected()) {

                fetchListApi();
            }


        }, throwable -> {

            //Would be called if callable returns null and no database value exists
            if (throwable.getLocalizedMessage().contains("null")) {
                if (isConnected()) {
                    fetchListApi();
                } else {
                    messageLabel.set(context.getString(R.string.error_message_loading_internet));
                    progressBar.set(View.GONE);
                    userLabel.set(View.VISIBLE);
                    userRecycler.set(View.GONE);
                }
            }
        });
    }

    //It is for initlialising views
    private void initializeViews() {
        userLabel.set(View.GONE);
        userRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }


    /**
     * Method to class API to get canada list
     */
    private void fetchListApi() {

        CustomApplication customApplication = CustomApplication.create(context);
        UsersService usersService = customApplication.getUserService();

        Disposable disposable = usersService.fetchUsers(RANDOM_USER_URL)
                .subscribeOn(customApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userResponse -> {

                    this.userList.clear();

                    insertIntoLocal(userResponse);
                    isLoading.set(false);
                    //Data Response
                    updateActionBartitle(userResponse.getTitle());
                    updateUserDataList(userResponse.getRows());
                    progressBar.set(View.GONE);
                    userLabel.set(View.GONE);
                    userRecycler.set(View.VISIBLE);
                }, throwable -> {
                    isLoading.set(false);
                    //Would be called when API throws an exception
                    messageLabel.set(context.getString(R.string.error_message_loading_users));
                    progressBar.set(View.GONE);
                    userLabel.set(View.VISIBLE);
                    userRecycler.set(View.GONE);
                });

        compositeDisposable.add(disposable);
    }


    /**
     * Insert into local database when internet is connected
     *
     * @param userResponse response received by API
     */
    private void insertIntoLocal(CountryFactsModels userResponse) {

        Completable.fromAction(() -> {
            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setTitle(userResponse.getTitle());
            List<RowEntity> rowEntities = new ArrayList<>();
            for (int i = 0; i < userResponse.getRows().size(); i++) {
                Row row = userResponse.getRows().get(i);
                if (!checkIfRowIsNull(row)) {
                    RowEntity rowEntity = new RowEntity();
                    rowEntity.setTitle(row.getTitle());
                    rowEntity.setDescription(row.getDescription());
                    rowEntity.setImageHref(row.getImageHref());
                    rowEntities.add(rowEntity);
                }
            }

            countryEntity.setRows(rowEntities);
            countryRepo.saveCountryInfo(countryEntity);

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }


    private void updateActionBartitle(String title) {
        messageheader.set(title);
    }

    private void updateUserDataList(List<Row> rowList) {
        userList.addAll(removedNullList(rowList));
        setChanged();
        notifyObservers();
    }


    /**
     * This method would add only rows which are not null
     *
     * @param rowList List passed after reponse
     * @return Null removed List
     */
    private List<Row> removedNullList(List<Row> rowList) {
        List<Row> removed = new ArrayList<>();
        for (int i = 0; i < rowList.size(); i++) {
            if (!checkIfRowIsNull(rowList.get(i))) {
                removed.add(rowList.get(i));
            }
        }
        return removed;
    }


    /**
     * This method will check if Condition matches that all items to be displayed are null
     *
     * @param row Model would be passed to check condition
     * @return Would return true if all items are null
     */
    private boolean checkIfRowIsNull(Row row) {
        boolean isRowNull = false;
        if (row.getTitle() == null && row.getDescription() == null && row.getImageHref() == null) {
            isRowNull = true;
        }
        return isRowNull;
    }


    /**
     * Method to Check Internet Connection
     *
     * @return false if internet is not connected
     */
    private boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connected;
    }


    public List<Row> getUserList() {
        return userList;
    }

    /**
     * This method would unsubscrible from Observable
     */
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

    /* Needs to be public for Databinding */
    public void onRefresh() {
        //this.userList.clear();
        isLoading.set(true);
        if (isConnected()) {
            fetchListApi();
        } else {
            fetchLocalApi();
        }
    }
}

