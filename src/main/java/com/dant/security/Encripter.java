package com.dant.security;

public class Encripter {

    private static int key = 10;

    public static String encrypt(String password) {
        return executeEncryption(password);
    }

    private static String executeEncryption(String password) {
        String encryptedPassword = "";
        for (int i = 0; i < password.length(); i++) {
            int c = password.charAt(i) ^ key;
            encryptedPassword = encryptedPassword + (char) c;
        }
        return encryptedPassword;
    }
}