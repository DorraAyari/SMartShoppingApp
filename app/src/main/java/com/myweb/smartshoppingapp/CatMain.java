package com.myweb.smartshoppingapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myweb.smartshoppingapp.Model.Transaction;
import com.myweb.smartshoppingapp.Model.category;
import com.myweb.smartshoppingapp.Utils.Apis;
import com.myweb.smartshoppingapp.Utils.Apistwo;
import com.myweb.smartshoppingapp.Utils.CatService;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatMain extends AppCompatActivity {
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    TextView loginn;
    CatService catService;
    List<category> listCategory =new ArrayList<>();
    ListView listView;
    SearchView searchView;

    ArrayAdapter<category > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cat);
        drawerLayout=findViewById(R.id.drawer_layout);
        loginn=findViewById(R.id.loginn);
        searchView = (SearchView) findViewById(R.id.searchView);
        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goTologin();
        }

        listView=(ListView)findViewById(R.id.listView);

        listPersons();

        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(CatMain.this, CatActivity.class);
               intent.putExtra("ID","");
                intent.putExtra("NOMBRE","");

               startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(listCategory.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(CatMain.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });
    }



    void goTologin(){
        Intent i=new Intent(CatMain.this, LoginActivity.class);
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
    public void ClickLogout(View view){
        myEdit = sharedPreferences.edit();
        myEdit.putBoolean(projectconstant.IS_LOGIN, false);
        myEdit.commit();

        goTologin();

    };
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
    public void CLickNote(View view){
        redirectActivity(this,Note.class);
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

    public void listPersons(){
        catService = Apistwo.getCatService();
        Call<List<category>>call= catService.getPersonas();
        call.enqueue(new Callback<List<category>>() {
            @Override
            public void onResponse(Call<List<category>> call, Response<List<category>> response) {
                if(response.isSuccessful()) {
                    listCategory = response.body();
              listView.setAdapter(new CatAdapter(CatMain.this,R.layout.content_cat, listCategory));

                }
            }

            @Override
            public void onFailure(Call<List<category>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }




}
