package com.dant.security;

public class crypting {

    public String crypte ="" ;

     public crypting(String pwd) {
        this.setEncrypt(pwd);
    }

    public void setEncrypt(String password){

        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;
            crypte=crypte+(char)c;
        }
    }
    public String getCrypte() {
        return crypte;
    }
}
