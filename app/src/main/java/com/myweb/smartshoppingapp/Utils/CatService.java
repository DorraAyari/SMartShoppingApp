package com.myweb.smartshoppingapp.Utils;


import com.myweb.smartshoppingapp.Model.Transaction;
import com.myweb.smartshoppingapp.Model.category;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CatService {

    @GET("categories")
     Call<List<category>> getPersonas();

    @POST("registration/login/ad")
    Call<Map>addPersona(@Body category p);

    @PUT("categories/{id}")
    Call<category>updatePersona(@Body category categories, @Path("id") long id);

    @DELETE("categories/{id}")
    Call<category>deletePersona(@Path("id")long id);

}
