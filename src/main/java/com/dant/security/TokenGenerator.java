package com.dant.security;

import java.security.SecureRandom;
//import com.google.code.gson

public class TokenGenerator{

    protected static SecureRandom random = new SecureRandom();


    public TokenGenerator(){
    }

    public String generateToken() {
        long longToken = Math.abs( random.nextLong() );
        String random = Long.toString( longToken, 64);
        return (random);

    }

    public String getToken(String var){
        String preftoken = generateToken();
        return (preftoken+".datetime="+var);

    }
}
