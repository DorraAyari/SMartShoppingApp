package com.myweb.smartshoppingapp.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class utilisateur {

    @SerializedName("userId")
    @Expose
    private int id;

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Login [id=" + id + ", username=" + username + ", mobile=" +
                ",]";
    }

    public utilisateur() {
        super();
    }

    public utilisateur(int id,String username,String password) {
        this.id = id;
        this.username=username;
        this.password=password;

;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
