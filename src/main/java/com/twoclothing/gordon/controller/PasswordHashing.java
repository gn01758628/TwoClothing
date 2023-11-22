package com.twoclothing.gordon.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHashing {

    public static String hashPassword(String password) {
        try {
            // 使用 SHA-256 加密算法
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            // 轉換為十六進制表示
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 處理例外
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
//        String password = "123aA";
//        String hashedPassword = hashPassword(password);
//        System.out.println("Original Password: " + password);
//        System.out.println("Hashed Password: " + hashedPassword);
    	for( int i = 1  ; i <=10 ; i++ ) {
        	String pw= "hash"+i;
        	String pswd = BCrypt.withDefaults().hashToString(12, pw.toCharArray());
        	System.out.println(pswd);
        }
    }
}
