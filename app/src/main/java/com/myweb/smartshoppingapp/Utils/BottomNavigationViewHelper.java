package com.myweb.smartshoppingapp.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.myweb.smartshoppingapp.CatActivity;
import com.myweb.smartshoppingapp.CatMain;
import com.myweb.smartshoppingapp.MainActivity;
import com.myweb.smartshoppingapp.R;
import com.myweb.smartshoppingapp.TransactionActivity;
import com.myweb.smartshoppingapp.list;
import com.myweb.smartshoppingapp.maps;

import java.io.File;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");

    }

    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.action_home:
                        Intent intent1 = new Intent(context, MainActivity.class); //ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        callingActivity.overridePendingTransition(R.anim.fade, R.anim.fade);
                        break;

                    case R.id.action_discover:
                  Intent intent2 = new Intent(context, maps.class); //ACTIVITY_NUM = 1
               context.startActivity(intent2);
             callingActivity.overridePendingTransition(R.anim.fade, R.anim.fade);
           Toast.makeText(context, "Discover", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_favorites:
                     Intent intent3 = new Intent(context, list.class); //ACTIVITY_NUM = 2
                       context.startActivity(intent3);
                      callingActivity.overridePendingTransition(R.anim.fade, R.anim.fade);
                        break;
                    case R.id.share:
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                        // type of the content to be shared
                        sharingIntent.setType("text/plain");

                        // Body of the content
                        String shareBody = "Your Body Here";

                        // subject of the content. you can share anything
                        String shareSubject = "Your Subject Here";

                        // passing body of the content
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                        // passing subject of the content
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                        context.startActivity(Intent.createChooser(sharingIntent, "Share using"));
                        break;

                    case R.id.search:
                            Intent profileIntent = new Intent(context, CatMain.class); //ACTIVITY_NUM = 3
                        context.startActivity(profileIntent);
                        callingActivity.overridePendingTransition(R.anim.fade, R.anim.fade);
                        break;
                }

                return false;
            }

        });
    }
}
