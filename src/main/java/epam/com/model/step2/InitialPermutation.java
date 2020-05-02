package epam.com.model.step2;

import epam.com.model.step1.BitsOperations;
import epam.com.model.step1.InputMessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class InitialPermutation {
    private static final int [] IP = {58, 50, 42, 34, 26, 18, 10, 2,
                                      60, 52, 44, 36, 28, 20, 12, 4,
                                      62, 54, 46, 38, 30, 22, 14, 6,
                                      64, 56, 48, 40, 32, 24, 16, 8,
                                      57, 49, 41, 33, 25, 17,  9, 1,
                                      59, 51, 43, 35, 27, 19, 11, 3,
                                      61, 53, 45, 37, 29, 21, 13, 5,
                                      63, 55, 47, 39, 31, 23, 15, 7 };
    
    private List<String> initialPermutation;//IP
    private InputMessage inputMsg;
    private BitsOperations bitsOperations;
    private List<String> IPLeftParts;//Lo
    private List<String> IPRigthParts;//Ro


    public InitialPermutation() throws UnsupportedEncodingException {
        initialPermutation = new ArrayList();
        inputMsg = new InputMessage();
        fillIP();
        splitIntoParts();
    }

    private void fillIP(){
        for (String str: inputMsg.getMsgBlocks()) {
            bitsOperations = new BitsOperations();
            initialPermutation.add(bitsOperations.doPermutation(IP, str));
        }
    }

    private void splitIntoParts(){
        IPLeftParts = new ArrayList<>();
        IPRigthParts = new ArrayList<>();
        for (String e: initialPermutation) {
            IPLeftParts.add(e.substring(0,32));
            IPRigthParts.add(e.substring(32,64));
        }
    }

    public List<String> getIPLeftParts() {
        return IPLeftParts;
    }

    public List<String> getIPRigthParts() {
        return IPRigthParts;
    }

    @Override
    public String toString() {
        return "initialPermutation= " + initialPermutation + " " + inputMsg.getMsgBlocks() + IPLeftParts.toString()
                + IPRigthParts.toString();
    }
}
