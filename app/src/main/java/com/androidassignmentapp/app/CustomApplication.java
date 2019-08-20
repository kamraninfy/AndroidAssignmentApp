package com.androidassignmentapp.app;

import android.app.Application;
import android.content.Context;

import com.androidassignmentapp.network.ApiFactory;
import com.androidassignmentapp.network.UsersService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class CustomApplication extends Application {

    private UsersService usersService;
    private Scheduler scheduler;

    private static CustomApplication get(Context context) {
        return (CustomApplication) context.getApplicationContext();
    }

    public static CustomApplication create(Context context) {
        return CustomApplication.get(context);
    }

    public UsersService getUserService() {
        if (usersService == null) {
            usersService = ApiFactory.create();
        }
        return usersService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return scheduler;
    }
}
