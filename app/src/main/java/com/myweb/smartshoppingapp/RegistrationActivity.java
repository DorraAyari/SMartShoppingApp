package com.myweb.smartshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.myweb.smartshoppingapp.pojo.UserForm;
import com.myweb.smartshoppingapp.retrofit.BaseEndPoint;
import com.myweb.smartshoppingapp.retrofit.RegistrationEndPoint;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;


import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    TextView tvNavLogin;

    TextInputLayout tv_username,tv_password,tv_confirmedpassword,tv_email;
    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goToDashbord();
        }
        tvNavLogin = findViewById(R.id.tv_nav_login);

        tv_username = findViewById(R.id.tv_username);
        tv_password = findViewById(R.id.tv_password);
        tv_email = findViewById(R.id.tv_email);
        tv_confirmedpassword = findViewById(R.id.tv_confirmedpassword);
        btn_reg = findViewById(R.id.btn_reg);



        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = checkValidation();
                if(validation){

                UserForm userForm = setLoginRegistrationData();
                    RegistrationEndPoint registrationEndPoint = BaseEndPoint.retrofit.create(RegistrationEndPoint.class);
                    Call<Map> addNewUser =  registrationEndPoint.putNewDataOnDb(userForm);
                    SweetAlertDialog pDialog = new SweetAlertDialog(RegistrationActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));


                    addNewUser.enqueue(new Callback<Map>() {
                        @Override
                        public void onResponse(Call<Map> call, Response<Map> response) {
                            pDialog.hide();


                            if(response.body().get("message").toString().equalsIgnoreCase("Inserted successfully"))
                            {
                                myEdit = sharedPreferences.edit();
                                myEdit.putBoolean(projectconstant.IS_LOGIN, true);
                                myEdit.putString(projectconstant.LOGIN_USER, tv_username.getEditText().getText().toString().trim());
                                goToDashbord();
                                myEdit.commit();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map> call, Throwable t) {
                            pDialog.hide();
                            new SweetAlertDialog(RegistrationActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText(t.getMessage())
                                    .show();
                        }
                    });


                }
            }

        });


        tvNavLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void goToDashbord(){
        Intent i=new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
    private UserForm setLoginRegistrationData() {
        UserForm userForm = new UserForm();





        userForm.setPassword(tv_password.getEditText().getText().toString());

        userForm.setUsername(tv_username.getEditText().getText().toString());
        userForm.setEmail(tv_email.getEditText().getText().toString());

        return userForm;
    }

    private boolean checkValidation() {

        boolean validation = true;



        if(tv_username.getEditText().getText().toString().trim().length()<1){
            tv_username.getEditText().setError("Pls, Enter Username");
            validation = false;
        }

        if(tv_password.getEditText().getText().toString().trim().length()<1){
            tv_password.getEditText().setError("Pls, Enter Password");
            validation = false;
        }
        if(tv_email.getEditText().getText().toString().trim().length()<1){
            tv_email.getEditText().setError("Pls, Enter Email");
            validation = false;
        }
        else{
            String email = tv_email.getEditText().getText().toString().trim();

            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if(!email.matches(emailPattern)){
                tv_email.getEditText().setError("Pls, Enter Valid Email Address");
                validation = false;
            }}
        if(tv_confirmedpassword.getEditText().getText().toString().trim().length()<1){
            tv_confirmedpassword.getEditText().setError("Pls, Enter Confirm Password");
            validation = false;
        }
        else if(!tv_password.getEditText().getText().toString().equals(tv_confirmedpassword.getEditText().getText().toString()))
        {
            tv_confirmedpassword.getEditText().setError("Pls, Enter Both Password Same");
            validation = false;
        }
            return validation;
    }


}