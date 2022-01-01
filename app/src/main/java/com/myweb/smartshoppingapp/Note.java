package com.myweb.smartshoppingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;

import java.util.ArrayList;

public class Note extends AppCompatActivity {
EditText et_text;
Button bt_add;
ListView list_view;
DatabaseHelper databaseHelper;
ArrayList arrayList;
ArrayAdapter arrayAdapter;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    TextView loginn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        drawerLayout=findViewById(R.id.drawer_layout);
        loginn=findViewById(R.id.loginn);

        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goTologin();
        }
        et_text=findViewById(R.id.et_text);
        bt_add=findViewById(R.id.bt_add);
        list_view=findViewById(R.id.list_view);
        databaseHelper =new DatabaseHelper(Note.this);
        arrayList=databaseHelper.getAllText();
        arrayAdapter =new ArrayAdapter(Note.this,
                android.R.layout.simple_list_item_1,arrayList);
        list_view.setAdapter(arrayAdapter);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=et_text.getText().toString();
                if(!text.isEmpty()){
                    if(databaseHelper.addText(text)){
                        et_text.setText("");
                        Toast.makeText(getApplicationContext(),"Data Inserted ...",Toast.LENGTH_SHORT).show();
                        arrayList.clear();
                        arrayList.addAll(databaseHelper.getAllText());
                        arrayAdapter.notifyDataSetChanged();
                        list_view.invalidateViews();
                        list_view.refreshDrawableState();

                    }
                }

            }
        });

    }
    void goTologin(){
        Intent i=new Intent(Note.this, LoginActivity.class);
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
    public void CLickNote(View view){
        redirectActivity(this,Note.class);
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
        redirectActivity(this, CatMain.class);
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