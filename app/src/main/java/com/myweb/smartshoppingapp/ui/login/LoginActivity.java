package com.myweb.smartshoppingapp.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.myweb.smartshoppingapp.MainActivity;

import com.myweb.smartshoppingapp.MainActivity3;
import com.myweb.smartshoppingapp.R;
import com.myweb.smartshoppingapp.RegistrationActivity;
import com.myweb.smartshoppingapp.pojo.utilisateur;
import com.myweb.smartshoppingapp.retrofit.BaseEndPoint;
import com.myweb.smartshoppingapp.retrofit.LoginEndPoint;
import com.myweb.smartshoppingapp.util.projectconstant;

import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView tvNavRegistration;
    TextInputLayout tv_username, tv_password,tv_email;
    Button btn_login;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goToDashbord();
        }
        tvNavRegistration = findViewById(R.id.tv_nav_registration);
        tv_username = findViewById(R.id.tv_username);
        tv_password = findViewById(R.id.tv_password);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = checkValidation();
                if (validation) {

                    utilisateur user = new utilisateur();
                    user.setUsername(tv_username.getEditText().getText().toString().trim());
                    user.setPassword(tv_password.getEditText().getText().toString().trim());

                    LoginEndPoint loginEndPoint = BaseEndPoint.retrofit.create(LoginEndPoint.class);
                    Call<Map> checkMapCall = loginEndPoint.checkUserNameAndPassword(user);
                    SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);


                    checkMapCall.enqueue(new Callback<Map>() {
                        @Override
                        public void onResponse(Call<Map> call, Response<Map> response) {
                            pDialog.hide();


                            if(response.body().get("message").toString().equalsIgnoreCase("login successfully"))
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
                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText(t.getMessage())
                                    .show();
                        }
                    });
                }

            }

        });

        tvNavRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    void goToDashbord(){
        Intent i=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
    public void onLoginClick(View View){
        startActivity(new Intent(this,RegistrationActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
    private boolean checkValidation() {
        boolean validation = true;
        if (tv_username.getEditText().getText().toString().trim().length() < 1) {
            tv_username.getEditText().setError("Pls, Enter Username");
            validation = false;
        }
        if (tv_password.getEditText().getText().toString().trim().length() < 1) {
            tv_password.getEditText().setError("Pls, Enter Password");
            validation = false;
        }

        return validation;
    }
}