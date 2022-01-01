package com.myweb.smartshoppingapp.retrofit;

import com.myweb.smartshoppingapp.pojo.UserForm;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistrationEndPoint {

    @POST("registration/login/add")
    Call<Map> putNewDataOnDb(@Body UserForm userForm);

}
