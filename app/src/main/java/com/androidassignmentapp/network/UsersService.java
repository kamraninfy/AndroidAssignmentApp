package com.androidassignmentapp.network;


import com.androidassignmentapp.model.CountryFactsModels;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Ahmad Shubita on 8/1/17.
 */

public interface UsersService {

    @GET
    Observable<CountryFactsModels> fetchUsers(@Url String url);
}
