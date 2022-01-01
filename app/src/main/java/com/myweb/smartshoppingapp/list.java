package com.myweb.smartshoppingapp;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.myweb.smartshoppingapp.Model.Transaction;
import com.myweb.smartshoppingapp.Model.category;
import com.myweb.smartshoppingapp.Utils.Apis;
import com.myweb.smartshoppingapp.Utils.CatService;
import com.myweb.smartshoppingapp.Utils.TransactionService;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class list extends AppCompatActivity {
    TransactionService personneService;
    List<Transaction> listPersonne = new ArrayList<>();
    ArrayAdapter<Transaction> listt;
    ListView listView;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    TextView loginn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activty_list);

        drawerLayout=findViewById(R.id.drawer_layout);
        loginn=findViewById(R.id.loginn);

        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goTologin();
        }

        listView = (ListView) findViewById(R.id.listView);

        listPersonne();

        FloatingActionButton fab = findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(list.this, TransactionActivity.class);
                intent.putExtra("ID", "");
                intent.putExtra("Montant", "");
                intent.putExtra("description", "");
                intent.putExtra("day", "");
                startActivity(intent);
            }
        });

    }

    void goTologin(){
        Intent i=new Intent(list.this, LoginActivity.class);
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
    public void listPersonne() {
        personneService = Apis.getTransactionService();
        Call<List<Transaction>> call = personneService.gettransaction() ;

        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful()) {
                    listPersonne = response.body();
         listView.setAdapter(new TransactionAdapter(list.this, R.layout.content_main, listPersonne));

                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }
}