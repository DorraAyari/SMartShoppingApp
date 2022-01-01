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

public interface TransactionService {

    @GET("transactions")
    Call<List<Transaction>> gettransaction();
    @POST("registration/login/add")
    Call<Map> addPersonne(@Body Transaction p);


    @PUT("transactions/{id}")
    Call<Transaction>updatePersonne(@Body Transaction transactions, @Path("id") long id);

    @DELETE("transactions/{id}")
    Call<Transaction>deletePersonne(@Path("id") long id);

}
