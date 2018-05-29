package com.dant.entity.dto;

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


    public String getPseudo() {return pseudo;}
    public String getKey() {return key;}
    public String getCurrentTime() {return currentTime;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}
    public void setKey(String key) {this.key = key;}
    public void setCurrentTime(String currentTime) {this.currentTime = currentTime;}
}