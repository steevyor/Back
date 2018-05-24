package com.dant.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class crypting {


    protected String pwd="";

    public crypting(String pwd, String key) {
        this.setEncrypt(pwd,key);
    }

    public String setEncrypt(String password, String key){

        try
        {
            Key clef = new SecretKeySpec(key.getBytes("UTF-8"),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE,clef);
            return new String(cipher.doFinal(password.getBytes()));
        }
        catch (Exception e)
        {
            return null;
        }

        /*for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;
            this.pwd=this.pwd+(char)c;
        }*/
    }

    public String getPwd() {
        return pwd;
    }


}
