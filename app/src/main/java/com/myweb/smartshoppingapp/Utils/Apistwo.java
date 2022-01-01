package com.myweb.smartshoppingapp.Utils;

public class Apistwo {
    public static final String URL_001="http://10.0.2.2:8080/api/";

    public static CatService getCatService(){
        return  Cliente.getClient(URL_001).create(CatService.class);
    }
}