package com.myweb.smartshoppingapp;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.myweb.smartshoppingapp.Model.Transaction;
import com.myweb.smartshoppingapp.Utils.Apis;
import com.myweb.smartshoppingapp.Utils.BottomNavigationViewHelper;
import com.myweb.smartshoppingapp.Utils.TransactionService;
import com.myweb.smartshoppingapp.ui.login.LoginActivity;
import com.myweb.smartshoppingapp.util.projectconstant;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionActivity extends AppCompatActivity {

    TransactionService service;
    TextInputLayout txtday;
    //Intialize Date Picker
    private DatePickerDialog datePickerDialog;
    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    TextView loginn;
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = TransactionActivity.this;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personne_layout);
        setupBottomNavigationView();
        drawerLayout=findViewById(R.id.drawer_layout);
        loginn=findViewById(R.id.loginn);

        sharedPreferences = getSharedPreferences(projectconstant.PREF_NAME,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(projectconstant.IS_LOGIN,false)){
            goTologin();
        }
        TextView idper=(TextView)findViewById(R.id.Id);
        EditText txtId=(EditText)findViewById(R.id.idtrans);
        TextView idc=(TextView)findViewById(R.id.idc);
        EditText idcategory=(EditText)findViewById(R.id.idcategory);

        TextView apellidos=(TextView)findViewById(R.id.description);
        final EditText txtApellidos=(EditText)findViewById(R.id.txtdescription);
        TextView montant=(TextView)findViewById(R.id.montant);
        final EditText txtmontant=(EditText)findViewById(R.id.txtmontant);
        TextView select=(TextView)findViewById(R.id.select);

        final CheckBox txtcheckBox1=(CheckBox)findViewById(R.id.txtcheckBox1);
        final CheckBox txtcheckBox2=(CheckBox)findViewById(R.id.txtcheckBox2);
        final CheckBox txtcheckBox3=(CheckBox)findViewById(R.id.txtcheckBox3);
        TextView nombres=(TextView)findViewById(R.id.nomCat);
        final EditText txtNombres=(EditText)findViewById(R.id.txtnomCat);
        Button btnSave=(Button)findViewById(R.id.btnSave);

        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        final String idca = bundle.getString("ID CAT");
        String ape=bundle.getString("DESCRIPTION");
        String mn=bundle.getString("MONTANT");
        String dayy=bundle.getString("dayy");
        String nom=bundle.getString("NOMCAT");
        String checkBox1=bundle.getString("check1");
        String checkBox2=bundle.getString("check2");
        String checkBox3=bundle.getString("check2");

        idcategory.setText(idca);
        txtId.setText(id);

        txtNombres.setText(nom);
        txtApellidos.setText(ape);
        //DatePicker Assign
        txtday = findViewById(R.id.txtday);
        initDatePicker();

        //Input Keyboard Off
        txtday.getEditText().setInputType(InputType.TYPE_NULL);
        txtday.getEditText().setText(getTodaysDate());

        //Onclikc Event Handle
        txtday.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        txtmontant.setText(mn);
        if(id.trim().length()==0||id.equals("")){
            idper.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
            idc.setVisibility(View.INVISIBLE);
            idcategory.setVisibility(View.INVISIBLE);

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transaction p=new Transaction();

                p.setDescription(txtApellidos.getText().toString());
                p.setnomCat(txtNombres.getText().toString());
                p.setMontant(txtmontant.getText().toString());

                p.setDay(txtday.getEditText().getText().toString());
                if(txtcheckBox1.isChecked()){
                    p.setType(txtcheckBox1.getText().toString());}
                else if (txtcheckBox2.isChecked()){
                    p.setType(txtcheckBox2.getText().toString());}
                else if (txtcheckBox3.isChecked()){
                    p.setType(txtcheckBox3.getText().toString());}

                if(id.trim().length()==0||id.equals("")){
                    addPersona(p);
                    Intent intent =new Intent(TransactionActivity.this,list.class);
                    startActivity(intent);
                }else{
                    updatePersona(p, valueOf(id));
                    Intent intent =new Intent(TransactionActivity.this,list.class);
                    startActivity(intent);
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersona(valueOf(id));
                Intent intent =new Intent(TransactionActivity.this,list.class);
                startActivity(intent);
            }
        });


    }

    public void addPersona(Transaction p){
        service= Apis.getTransactionService();
        Call<Map>call=service.addPersonne(p);
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TransactionActivity.this,"Transaction add",Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(TransactionActivity.this,list.class);
        startActivity(intent);
    }
    public void updatePersona(Transaction p, int id){
        service= Apis.getTransactionService();
        Call<Transaction>call=service.updatePersonne(p,id);
        call.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TransactionActivity.this,"Transaction modify",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(TransactionActivity.this,list.class);
        startActivity(intent);
    }
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    public void deletePersona(int id){
        service=Apis.getTransactionService();
        Call<Transaction>call=service.deletePersonne(id);
        call.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if(response.isSuccessful()){
                    Toast.makeText(TransactionActivity.this,"transaction delete",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(TransactionActivity.this,list.class);
        startActivity(intent);
    }
    //DATE PICKER PRE_DEFINE CODE
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day,month,year);
                txtday.getEditText().setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return day + " " + getMonthFormat(month) + " , " + year;
    }
    void goTologin(){
        Intent i=new Intent(TransactionActivity.this, LoginActivity.class);
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
    public void ClickCalculator(View view){
        redirectActivity(this,CalculatorActivity.class);
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
    private String getMonthFormat(int month) {
        if(month == 1)
            return "January";
        if(month == 2)
            return "Febuary";
        if(month == 3)
            return "March";
        if(month == 4)
            return "April";
        if(month == 5)
            return "May";
        if(month == 6)
            return "June";
        if(month == 7)
            return "July";
        if(month == 8)
            return "Auguest";
        if(month == 9)
            return "September";
        if(month == 10)
            return "October";
        if(month == 11)
            return "November";
        if(month == 12)
            return "December";
        //default month
        return "January";

    }

}