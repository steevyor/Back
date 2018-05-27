package com.dant.entity;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

public class Token {

    private static SecureRandom random = new SecureRandom();
    String currentTime;
    private String key;


    public Token(){
        System.out.println("Token : creating instance now");
        generateTokenKey();
        setTimer();
        System.out.println("Token : token instance successfully created");
    }

    public Token(String key){
        this.key = key;
        setTimer();
    }

    private void generateTokenKey(){
        System.out.println("    Token : generating token key now");
        this.key = Long.toString( Math.abs( random.nextLong() ), 64);
        System.out.println("    Token : token key generated : "+this.key);
    }

    private void setTimer(){
        System.out.println("    Token : setting timer now");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("    Token : LocalDateTime initialised");
        this.currentTime= new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(now);
        System.out.println("    Token : current time set with SimpleDateFormat : "+this.currentTime);
    }

    public void updateTimer(){
        LocalDateTime now = LocalDateTime.now();
        this.currentTime= new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(now);
    }

}
