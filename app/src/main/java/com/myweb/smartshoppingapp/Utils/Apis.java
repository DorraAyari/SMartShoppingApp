package com.myweb.smartshoppingapp.Utils;

public class Apis {

    public static final String URL_001="http://10.0.2.2:8080/api/";

    public static TransactionService getTransactionService(){
        return  Cliente.getClient(URL_001).create(TransactionService.class);
    }
}
