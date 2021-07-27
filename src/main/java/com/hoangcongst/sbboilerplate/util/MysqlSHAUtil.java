package com.hoangcongst.sbboilerplate.util;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MysqlSHAUtil {
    private static final SecretKey key;

    static {
        byte[] keyBytes = Arrays.copyOf("secretpassword".getBytes(StandardCharsets.US_ASCII), 16);
        key = new SecretKeySpec(keyBytes, "AES");
    }

    public static String decrypt(String password) {
        try {
            Cipher decipher = Cipher.getInstance("AES");

            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decodeHex = Hex.decode(password);

            byte[] ciphertextBytes = decipher.doFinal(decodeHex);

            return new String(ciphertextBytes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String encrypt(String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] cleartext = password.getBytes(StandardCharsets.UTF_8);
            byte[] ciphertextBytes = cipher.doFinal(cleartext);

            return new String(Hex.encode(ciphertextBytes));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } return null;
    }
}
