package com.example.fabio.cardboardpb.Manager;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by matteo on 04/03/2015.
 */
public class PasswdManager {


    private static String salt = "12ThisIsMySaltForAddedSecurity*59";

    public static String calculateHash(String data) {

        String hash = new String(Hex.encodeHex(DigestUtils.sha256(data)));
        return hash;
    }

   /** public static void main(String args[]) {

        System.out.println("SHA256 Hash: " + calculateHash("ciao"));

    }**/


}
