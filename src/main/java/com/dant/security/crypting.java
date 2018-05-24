package com.dant.security;

public class crypting {


    protected String pwd="";

    public crypting(String pwd) {
        this.setEncrypt(pwd);
    }

    public void setEncrypt(String password){
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;
            this.pwd=this.pwd+(char)c;
        }
    }

    public String getPwd() {
        return pwd;
    }


}
