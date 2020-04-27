package epam.com.model;

import java.util.*;

public class Key {
    private static final int[] NUM_LEFT_SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    private static final int[] PC_1 = { 57, 49, 41, 33, 25, 17,  9,
                                         1, 58, 50, 42, 34, 26, 18,
                                        10,  2, 59, 51, 43, 35, 27,
                                        19, 11,  3, 60, 52, 44, 36,
                                        63, 55, 47, 39, 31, 23, 15,
                                         7, 62, 54, 46, 38, 30, 22,
                                        14,  6, 61, 53, 45, 37, 29,
                                        21, 13,  5, 28, 20, 12,  4};

    private static final int[] PC_2 = {14, 17, 11, 24,  1,  5,
                                        3, 28, 15,  6, 21, 10,
                                       23, 19, 12,  4, 26,  8,
                                       16,  7, 27, 20, 13,  2,
                                       41, 52, 31, 37, 47, 55,
                                       30, 40, 51, 45, 33, 48,
                                       44, 49, 39, 56, 34, 53,
                                       46, 42, 50, 36, 29, 32};

    private String key = "";
    private String keyLeftPart;//Co
    private String keyRigthPart;//Do
    private List<String> keyLeftParts;//Cn
    private List<String> keyRigthParts;//Dn
    private List<String> keyN;//Kn
    private BitsOperations bitsOperations;

    public Key() {
        generateKey();
        splitIntoParts();
        createSixteenBlocks();
        bitsOperations = new BitsOperations();
        generateKeyN();
    }

    private void generateKey() {
        int[] binaryArray = new int[7];
        String binaryKey = "";
        Random rand = new Random();
        String beginningZeros;

        for (int i = 0; i < binaryArray.length; i++) {
            binaryArray[i] = rand.nextInt(256);
        }

        for (int b : binaryArray) {
            beginningZeros = "";
            binaryKey = Integer.toBinaryString(b);
            for (int i = 0; i < 8 - binaryKey.length(); i++) {
                beginningZeros += "0";
            }
            key = key + beginningZeros + binaryKey;
        }
    }

    private void generateKeyN(){
        keyN = new ArrayList<>();
        List<String> CnDn = bitsOperations.combine(keyLeftParts, keyRigthParts);
        for (String e: CnDn) {
           keyN.add(bitsOperations.doPermutation(PC_2, e));
        }

    }

    private void splitIntoParts() {
        keyLeftPart = key.substring(0, 28);
        keyRigthPart = key.substring(28, 56);
    }

    private void createSixteenBlocks() {
        String left = keyLeftPart;
        String right = keyRigthPart;
        keyLeftParts = new ArrayList<>();
        keyRigthParts = new ArrayList<>();


        for (int e : NUM_LEFT_SHIFTS) {
            left = doLeftShift(left, e);
            right = doLeftShift(right, e);
            keyLeftParts.add(left);
            keyRigthParts.add(right);
        }
    }

    private String doLeftShift(String partKey, int shift) {
        char[] shiftingArray = partKey.toCharArray();
        int size = shiftingArray.length;
        char[] temp = new char[size];
        int j = 0;

        for (int i = 0; i < size; i++) {
            if (size - shift + i >= size) {
                temp[i] = shiftingArray[j];
                j = j + 1;
            } else {
                temp[i] = shiftingArray[size - shift + i];
            }
        }
        return String.copyValueOf(temp);
    }

    public String getKey() {
        return key;
    }

    public String getKeyLeftPart() {
        return keyLeftPart;
    }

    public String getKeyRigthPart() {
        return keyRigthPart;
    }

    public List<String> getKeyLeftParts() {
        return keyLeftParts;
    }

    public List<String> getKeyRigthParts() {
        return keyRigthParts;
    }

    public List<String> getKeyN() {
        return keyN;
    }
}
