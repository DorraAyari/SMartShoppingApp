package com.myweb.smartshoppingapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserForm {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName(" confirmedPassssword")
    @Expose
    private String  confirmedPassword;

    @SerializedName("email")
    @Expose
    private String email;

    public UserForm() {
    }

    public UserForm(String username,String paasword,String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPasssword(String confirmedPasssword) {
        this.confirmedPassword = confirmedPasssword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


