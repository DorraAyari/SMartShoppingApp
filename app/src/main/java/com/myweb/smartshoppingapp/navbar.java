package com.myweb.smartshoppingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.myweb.smartshoppingapp.R;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;

public class navbar extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
DrawerLayout drawerLayout;
Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawer_layout);

        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goTologin();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myEdit = sharedPreferences.edit();
                myEdit.putBoolean(projectconstant.IS_LOGIN, false);
                myEdit.commit();
                goTologin();
            }
        });
    }
    public void ClickMenu(View view){
openDrawer(drawerLayout);
    }
    void goTologin(){
        Intent i=new Intent(navbar.this, LoginActivity.class);
        startActivity(i);
        finish();
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
    public void ClickAboutUs(View view){
        redirectActivity(this,list.class);
    }
    public void ClickLogout(View view){
       logoutt(this);
    }

    public static void logoutt(Activity activity) {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public static void redirectActivity(Activity activity,Class aClass) {
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