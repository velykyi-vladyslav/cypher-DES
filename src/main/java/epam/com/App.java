package epam.com;

import epam.com.model.BitsOperations;
import epam.com.model.InputMessage;
import epam.com.model.Key;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
     Key key = new Key();
        System.out.println("Key: " + key.getKey());
        System.out.println("Left part of key: " + key.getKeyLeftPart());
        System.out.println("Right part of key: " + key.getKeyRigthPart());

        System.out.println("Cn:");
        System.out.println(key.getKeyLeftParts());
        System.out.println("Dn:");
        System.out.println(key.getKeyRigthParts());
        System.out.println("Kn: ");
        System.out.println(key.getKeyN());
    }

}
