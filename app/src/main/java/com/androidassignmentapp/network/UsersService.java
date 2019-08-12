package com.androidassignmentapp.network;


import com.androidassignmentapp.model.CountryFactsModels;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Class which will write the API and URLs and make service call.
 */

public interface UsersService {

    @GET
    Observable<CountryFactsModels> fetchUsers(@Url String url);
}
