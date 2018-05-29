package com.dant.security;

public class Decrypted {


    public static String decrypt(String pwd){
        return setCrypt(pwd);
    }

    public static String setCrypt(String password){

        String crypte = "";
        for (int i=0; i<password.length();i++){
            int c=password.charAt(i)^98;
            crypte=crypte+(char)c;
        }

        return crypte;
    }

}
