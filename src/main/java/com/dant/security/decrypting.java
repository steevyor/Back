package com.dant.security;

public class decrypting {

    String crypte = "";

    public decrypting(String pwd){
        this.deCrypt(String pwd);
    }

    public void deCrypt(String password){

        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;
            crypte=crypte+(char)c;
        }
    }
}
