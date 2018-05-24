package com.dant.security;

import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class crypting {


    protected String pwd="";

    public crypting(String pwd) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        this.setEncrypt(pwd);
    }

    public void setEncrypt(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
    /*
        String Key = password;
        byte[] KeyData = Key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);
    */


    /*
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
    */


        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;
            this.pwd=this.pwd+(char)c;
        }

    }


    public void deCrypt(){
        String aCrypter= "";
        for (int i=0; i<this.pwd.length();i++)  {
            int c=this.pwd.charAt(i)^48;
            aCrypter=aCrypter+(char)c;
        }
    }



    public String getPwd() {
        return pwd;
    }


}
