package epam.com.model;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class Key {
    private SecretKey key = null;
    private String binaryKey;


    // plainText = input.nextLine().getBytes("UTF-8");
    // inputLine = Integer.toBinaryString(b);
    Random random;

    public Key() {
        random = new Random();
    }

    public void generateKey() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        SecureRandom rand = new SecureRandom();
        KeyGenerator generator;
        try {
            generator = KeyGenerator.getInstance("DES");
            generator.init(rand);
            generator.init(56);
            key = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("DES key is : ");
        System.out.println(Base64.getEncoder().encodeToString(key.getEncoded()));

    }


}
