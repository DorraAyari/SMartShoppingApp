package com.myweb.smartshoppingapp.retrofit;

import com.myweb.smartshoppingapp.pojo.utilisateur;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginEndPoint {

    @POST("login/check")
    Call<Map> checkUserNameAndPassword(@Body utilisateur user);

}
