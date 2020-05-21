package epam.com;

import epam.com.model.step1.Key;
import epam.com.model.step2.Encrypt;
import epam.com.model.step2.InitialPermutation;
import epam.com.model.step2.SBoxe;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Encrypt encrypt = new Encrypt(16);
        encrypt.finalEncrypt();
        System.out.println(encrypt.toString());


    }


}
