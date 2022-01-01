package com.myweb.smartshoppingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.myweb.smartshoppingapp.Model.category;
import com.myweb.smartshoppingapp.Utils.Apis;
import com.myweb.smartshoppingapp.Utils.Apistwo;
import com.myweb.smartshoppingapp.Utils.BottomNavigationViewHelper;
import com.myweb.smartshoppingapp.Utils.CatService;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatActivity extends AppCompatActivity {
    CatService service;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    TextView loginn;
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = CatActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_layout);
        setupBottomNavigationView();
        drawerLayout=findViewById(R.id.drawer_layout);
        loginn=findViewById(R.id.loginn);

        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goTologin();
        }
        TextView idper=(TextView)findViewById(R.id.Id);
        EditText txtId=(EditText)findViewById(R.id.txtId);
        TextView nombres=(TextView)findViewById(R.id.nomCat);
        final EditText txtNombres=(EditText)findViewById(R.id.txtnomCat);

        Button btnSave=(Button)findViewById(R.id.btnSave);

        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String nom=bundle.getString("NOMCAT");


        txtId.setText(id);
        txtNombres.setText(nom);

        if(id.trim().length()==0||id.equals("")){
            idper.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category p=new category();
                p.setnomCat(txtNombres.getText().toString());

                if(id.trim().length()==0||id.equals("")){
                    addPersona(p);
                    Intent intent =new Intent(CatActivity.this, CatMain.class);
                    startActivity(intent);
                }else{
                    updatePersona(p,Integer.valueOf(id));
                    Intent intent =new Intent(CatActivity.this, CatMain.class);
                    startActivity(intent);
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersona(Integer.valueOf(id));
                Intent intent =new Intent(CatActivity.this, CatMain.class);
                startActivity(intent);
            }
        });



    }
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public void addPersona(category p){
        service= Apistwo.getCatService();
        Call<Map>call=service.addPersona(p);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CatActivity.this,"Category add",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(CatActivity.this, CatMain.class);
        startActivity(intent);
    }
    public void updatePersona(category p, int id){
        service= Apistwo.getCatService();
        Call<category>call=service.updatePersona(p,id);
        call.enqueue(new Callback<category>() {
            @Override
            public void onResponse(Call<category> call, Response<category> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CatActivity.this,"Category modify",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<category> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(CatActivity.this, CatMain.class);
        startActivity(intent);
    }
    public void deletePersona(int id){
        service= Apistwo.getCatService();
        Call<category>call=service.deletePersona(id);
        call.enqueue(new Callback<category>() {
            @Override
            public void onResponse(Call<category> call, Response<category> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CatActivity.this,"Category delete",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<category> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(CatActivity.this, CatMain.class);
        startActivity(intent);
    }
    void goTologin(){
        Intent i=new Intent(CatActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        redirectActivity(this,MainActivity.class);
    }

    public void ClickDashboard(View view){
        redirectActivity(this,maps.class);
    }
    public void ClickCalendar(View view){
        redirectActivity(this,CalendarActivity.class);
    }
    public void ClickAboutUs(View view){
        redirectActivity(this,list.class);
    }
    public void ClickCat(View view){
        redirectActivity(this,CatMain.class);
    }
    public void ClickStat(View view){
        redirectActivity(this,MainActivity4.class);
    }

    public void ClickCalculator(View view){
        redirectActivity(this,CalculatorActivity.class);
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent=new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
