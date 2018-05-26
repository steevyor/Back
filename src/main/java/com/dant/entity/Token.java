package com.dant.entity;

import java.security.SecureRandom;

public class Token {

    private static SecureRandom random = new SecureRandom();
    private long timer;
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
        this.timer = System.currentTimeMillis() % 1000;
    }

    public void updateTimer(){
        this.timer = System.currentTimeMillis() % 1000;
    }

}
