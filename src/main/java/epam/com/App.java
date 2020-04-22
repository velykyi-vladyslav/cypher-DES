package epam.com;

import epam.com.model.BitsOperations;
import epam.com.model.InputMessage;
import epam.com.model.Key;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Key key = new Key();
        key.generateKey();
    }

}
