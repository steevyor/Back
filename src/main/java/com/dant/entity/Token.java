package com.dant.entity;

import jdk.vm.ci.meta.Local;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Token {

    private static SecureRandom random = new SecureRandom();
    String currentTime;
    private String key;
    LocalDateTime timer;


    public Token(){
        //System.out.println("Token : creating instance now");
        generateTokenKey();
        setTimer();
        //System.out.println("Token : token instance successfully created");
    }

    public Token(String key){
        this.key = key;
        setTimer();
    }

    public Token(String key,String currentTime){
        this.key = key;
        this.currentTime = currentTime;
    }


    private void generateTokenKey(){
        //System.out.println("    Token : generating token key now");
        this.key = Long.toString( Math.abs( random.nextLong() ), 64);
        //System.out.println("    Token : token key generated : "+this.key);
    }

    private void setTimer(){
        //System.out.println("    Token : setting timer now");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println("    Token : LocalDateTime initialised");
        this.timer = now;
        this.currentTime= now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss",Locale.ENGLISH));
        //System.out.println("    Token : current time set with SimpleDateFormat : "+this.currentTime);
    }

    public void updateTimer(){
        LocalDateTime now = LocalDateTime.now();
        this.timer = now;
        this.currentTime= new SimpleDateFormat("yyyyMMddHHmmss", Locale.FRANCE).format(now);
    }

    public String getTokenKey() {
        return this.key;
    }

    public String getTimer() {
        return this.currentTime;
    }

    public boolean isTimerGapValid(){
        LocalDateTime currentInteractionDateTime = LocalDateTime.now();
        //LocalDateTime previousInteractionDateTime = LocalDateTime.parse(newTimer);
        LocalDateTime previousInteractionDateTime = this.timer;

        int currentHour = currentInteractionDateTime.getHour();
        int currentDay = currentInteractionDateTime.getDayOfYear();
        int currentYear = currentInteractionDateTime.getYear();

        int previousHour = previousInteractionDateTime.getHour();
        int previousDay = previousInteractionDateTime.getDayOfYear();
        int previousYear = previousInteractionDateTime.getYear();

        if(currentYear != previousYear && currentDay >= 30){
            return false;
        }else if(currentDay >= (previousDay)+30){
            return false;
        }else return true;
    }
}
