package com.myweb.smartshoppingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class category {


    @SerializedName("idcat")
    @Expose
    public int idcat;

    @SerializedName("nomCat")
    @Expose
    private String nomCat;
    @SerializedName("idtransaction")
    @Expose
    private int idtransaction;

    public category(){

    }

    public category(int idcat, String nomCat) {
        this.idcat = idcat;
        this.nomCat = nomCat;

    }

    public int getidcat() {
        return idcat;
    }

    public void setidcat(int id) {
        this.idcat = idcat;
    }

    public String getnomCat() {
        return nomCat;
    }

    public void setnomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
    }

}