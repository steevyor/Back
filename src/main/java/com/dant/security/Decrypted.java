package com.dant.security;

public class Decrypter {

    String crypte = "";

    public static Decrypter(String pwd){this.setCrypt(pwd);}

    public String setCrypt(String password){
        for (int i=0; i<password.length();i++){
            int c=password.charAt(i)^98;
            crypte=crypte+(char)c;
        }

        return crypte;
    }

}
