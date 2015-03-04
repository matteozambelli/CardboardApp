package com.example.matteo.tmplogin;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by matteo on 04/03/2015.
 */
public class PasswdManager {

    private static String data = "password";
    private static String salt = "ThisIsMySaltForAddedSecurity";

    public static String calculateHash(String data, String salt) {
        return DigestUtils.sha256Hex(data + salt);
    }

    public static void main(String args[]) {
        System.out.println("SHA256 Hash: " + calculateHash(data, salt));
        System.out.println(calculateHash(data, salt));
    }


}
