package com.dant.security;

public class decrypting {

    String crypte = "";

    public decrypting(String pwd){this.setCrypt(pwd);}

    public void setCrypt(String password){
        for (int i=0; i<password.length();i++){
            int c=password.charAt(i)^98;
            crypte=crypte+(char)c;
        }
    }

    public String getCrypte() {
        return crypte;
    }
}
