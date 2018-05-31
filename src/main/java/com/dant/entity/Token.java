package com.dant.entity;

//import jdk.vm.ci.meta.Local;

import com.dant.Print;

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

    //GitTest


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
        this.currentTime= now.toString();
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
        Print.p("Token.isTimerGapValid : currentInteractionDateTime :"+currentInteractionDateTime);
        //LocalDateTime previousInteractionDateTime = LocalDateTime.parse(newTimer);
        String timer = this.currentTime;
        LocalDateTime previousInteractionDateTime = LocalDateTime.parse(timer);
        Print.p("Token.isTimerGapValid : previousInteractionDateTime : "+timer);

        int currentHour = currentInteractionDateTime.getHour();
        int currentDay = currentInteractionDateTime.getDayOfYear();
        int currentYear = currentInteractionDateTime.getYear();

        int previousHour = previousInteractionDateTime.getHour();
        int previousDay = previousInteractionDateTime.getDayOfYear();
        int previousYear = previousInteractionDateTime.getYear();

        if(currentYear != previousYear && currentDay >= 30){
            Print.p("Token.isTimerGapValid : False");
            return false;
        }else if(currentDay >= (previousDay)+30){
            Print.p("Token.isTimerGapValid : False");
            return false;
        }else {
            Print.p("Token.isTimerGapValid : True");
            return true;
        }
    }
}
