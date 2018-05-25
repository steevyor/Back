package com.dant.security;

public class crypting {

    public String crypte ="" ;

     public crypting(String pwd) {
        this.setEncrypt(pwd);
    }

    public String setEncrypt(String password){

        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^98;
            crypte=crypte+(char)c;
        }
        return crypte;
    }
}
