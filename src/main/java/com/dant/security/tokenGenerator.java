package com.dant.security;

import java.security.SecureRandom;

public class tokenGenerator {



    protected static SecureRandom random = new SecureRandom();
    protected long timer;

    public tokenGenerator(){
    }

    public String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 64);
        return random;
    }

    public String getToken(String var){
        String preftoken = generateToken();
        return (preftoken+".datetime="+var);

    }

    static tokenGenerator TokenGenerator() {
        tokenGenerator token = new tokenGenerator();
        token.generateToken();
        token.setMillis();
        return token;
    }

    public void setMillis() {
        this.timer = System.currentTimeMillis() % 1000;
    }

}
