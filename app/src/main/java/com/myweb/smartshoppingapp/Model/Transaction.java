package com.myweb.smartshoppingapp.Model;

import android.database.Cursor;
import android.icu.util.ULocale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

public class Transaction {

    @SerializedName("idtrans")
    @Expose
    private int idtrans;

    @SerializedName("idcat")
    @Expose
    private int idcategory;
    @SerializedName("idcategory")
    @Expose
    private int idcategoryy;
    @SerializedName("nomCat")
    @Expose
    private String nomCat;



    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("montant")
    @Expose
    private String montant;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("type")
    @Expose
    private String type;
    public Transaction(){

    }

    public Transaction(int id, String nombres,String montant,String type) {
        this.idtrans = id;

        this.description = nombres;
        this.montant = nombres;
        this.type=type;
    }

    public int getId() {
        return idtrans;
    }

    public void setId(int id) {
        this.idtrans = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String nombres) {
        this.description = nombres;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdcategoryy() {
        return idcategoryy;
    }

    public void setIdcategoryy(int idcategoryy) {
        this.idcategoryy = idcategoryy;
    }

    public String getnomCat() {
        return nomCat;
    }

    public void setnomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

}
