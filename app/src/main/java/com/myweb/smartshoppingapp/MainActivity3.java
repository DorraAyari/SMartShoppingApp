package com.myweb.smartshoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.github.paolorotolo.appintro.model.SliderPagerBuilder;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;

public class MainActivity3 extends AppIntro2 {
//SharedPreferences sharedPreferences;
  //SharedPreferences.Editor myEdit;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //  sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
     // if(sharedPreferences.getBoolean(projectconstant.INTRO_SCREEN,false)){
   //goToHome();
//}

//      myEdit = sharedPreferences.edit();
        //myEdit.putBoolean(projectconstant.INTRO_SCREEN,true);
//myEdit.commit();

         SliderPage SliderPage =new SliderPagerBuilder()

                .imageDrawable(R.drawable.money_bag)
                .bgColor(getColor(R.color.teal_200))
                .build();
        SliderPage SliderPage2 =new SliderPagerBuilder()

                .imageDrawable(R.drawable.welcome2)
                .bgColor(getColor(R.color.teal_200))
                .build();
        addSlide(AppIntro2Fragment.newInstance(SliderPage));

        addSlide(AppIntro2Fragment.newInstance(SliderPage2));
    }
void goToHome(){
    Intent i=new Intent(MainActivity3.this, LoginActivity.class);
    startActivity(i);
    finish();
}
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        goToHome();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        goToHome();
    }
}