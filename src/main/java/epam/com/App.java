package epam.com;

import epam.com.model.step1.Key;
import epam.com.model.step2.Encrypt;
import epam.com.model.step2.InitialPermutation;
import epam.com.model.step2.SBoxe;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Encrypt encrypt = new Encrypt(16);
        encrypt.encrypt();
        System.out.println(encrypt.toString());
    }

}
