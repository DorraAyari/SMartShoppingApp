package com.myweb.smartshoppingapp.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("id")
    @Expose
    private int loginId;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @Override
    public String toString() {
        return "Registration [id=" + id + ", loginId=" + loginId + ", email=\" + email + \", fullname=" + username + "]";
    }

    public Registration() {
        super();
    }


    public Registration(int id,int loginId,String username,String password) {
        this.id=id;
        this.loginId=loginId;
        this.username=username;
        this.password=password;

        ;
    }

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

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

