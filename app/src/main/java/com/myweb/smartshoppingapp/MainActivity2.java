package com.myweb.smartshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.mrtyvz.archedimageprogress.ArchedImageProgressBar;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;

public class MainActivity2 extends AppCompatActivity {
    private static int MainActivityOUT=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i=new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(i);
                finish();
            }
        },MainActivityOUT);
        ArchedImageProgressBar customTextArcProgress = findViewById(R.id.linkedin_progressBar);

        customTextArcProgress.setProgressText(new String[]{"Loading"}, "#c5cae9");
        customTextArcProgress.setProgressTextSize(13.0f);
        customTextArcProgress.setCircleSize(35.0f);
        customTextArcProgress.setArchSize(43.0f);
        customTextArcProgress.setCircleColor(Color.parseColor("#3949ab"));
        customTextArcProgress.setArchColor(Color.parseColor("#fb8c00"));
        customTextArcProgress.setArchLength(120);
        customTextArcProgress.setArchStroke(9.0f);
        customTextArcProgress.setArchSpeed(3);
    }

}