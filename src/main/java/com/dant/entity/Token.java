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
        generateTokenKey();
        setTimer();
    }

    public Token(String key){
        this.key = key;
        setTimer();
    }

    private void generateTokenKey(){
        this.key = Long.toString( Math.abs( random.nextLong() ), 64);
    }

    private void setTimer(){
        LocalDateTime now = LocalDateTime.now();
        this.currentTime= new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(now);
    }

    public void updateTimer(){
        LocalDateTime now = LocalDateTime.now();
        this.currentTime= new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(now);
    }

}
