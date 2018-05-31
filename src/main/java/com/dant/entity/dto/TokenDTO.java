package com.dant.entity.dto;

import com.dant.entity.Token;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TokenDTO {
    public String pseudo;
    public String key;
    public String currentTime;

    public TokenDTO(String key) {
        this.key = key;
    }

    public TokenDTO(String key, String currentTime) {
        this.key = key;
        this.currentTime = currentTime;
    }

    public TokenDTO(String key, String currentTime, String pseudo) {
        this.key = key;
        this.currentTime = currentTime;
        this.pseudo = pseudo;
    }

    public TokenDTO(Token t){
        this.key = t.getTokenKey();
        this.currentTime = t.getTimer();
        this.pseudo = pseudo;
    }


    public String getPseudo() {return pseudo;}
    public String getKey() {return key;}
    public String getCurrentTime() {return currentTime;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}
    public void setKey(String key) {this.key = key;}
    public void setCurrentTime(String currentTime) {this.currentTime = currentTime;}
    public void setCurrentTimeNow() {this.currentTime = LocalDateTime.now().toString();}
}