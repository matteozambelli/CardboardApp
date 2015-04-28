package it.unibg.p3d4amb.carracingcarboard.Manager;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by matteo on 04/03/2015.
 */
public class PasswordManager {

    public static String calculateHash(String data) {

        String hash = new String(Hex.encodeHex(DigestUtils.sha256(data)));
        return hash;
    }

/*  public static void main(String args[]) {
      SecureRandom random = new SecureRandom();
     System.out.println(new BigInteger(40, random).toString(32));
        System.out.println("SHA256 Hash: " + calculateHash(new BigInteger(40, random).toString(32)));

    }
*/

}
