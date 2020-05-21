package epam.com.model.step2;

import epam.com.model.step1.BitsOperations;
import epam.com.model.step1.Key;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Encrypt {
    private static final int[] E = {32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 30, 31, 32, 1};
    private static final int[] P = {16, 7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2, 8, 24, 14,
            32, 27, 3, 9,
            19, 13, 30, 6,
            22, 11, 4, 25};
    private static final int[] IP_TRANSPOSITED = {40, 8, 48, 16, 56, 24, 64, 32,
            39, 7, 47, 15, 55, 23, 63, 31,
            38, 6, 46, 14, 54, 22, 62, 30,
            37, 5, 45, 13, 53, 21, 61, 29,
            36, 4, 44, 12, 52, 20, 60, 28,
            35, 3, 43, 11, 51, 19, 59, 27,
            34, 2, 42, 10, 50, 18, 58, 26,
            33, 1, 41, 9, 49, 17, 57, 25};
    private static Logger logger = LogManager.getLogger(Encrypt.class);
    private int rounds;
    private InitialPermutation ip;
    private Key key;
    private List<String> keyN;
    private List<String> l;
    private List<String> r;
    private Xor xor;
    private SBoxe sBoxe;
    private BitsOperations bitsOperations;
    private List<String> encrypted;
    private List<String> rightParts;
    private List<String> leftParts;



    public Encrypt(int rounds) throws UnsupportedEncodingException {
        this.rounds = rounds;
        key = new Key();
        ip = new InitialPermutation();
        l = new ArrayList<>();
        r = new ArrayList<>();
        l = ip.getIPLeftParts();
        r = ip.getIPRigthParts();
        keyN = key.getKeyN();
        encrypted = new ArrayList<>();

    }

    public void finalEncrypt() {
        for (int i = 0; i < r.size(); i++) {
            encrypt(r.get(i), l.get(i));
        }
    }

    public void encrypt(String rightPart, String leftPart) {
        String twoPartsAfterFunc;
        bitsOperations = new BitsOperations();
        List<String> afterPermitation = new ArrayList<>();
        leftParts = createList(leftPart);
        rightParts = createList(rightPart);
        for (int i = 1; i <= rounds; i++) {
            leftParts.set(i, rightParts.get(i - 1));
            rightParts.set(i, calcFunc(i));
        }
        twoPartsAfterFunc = leftParts.get(rounds) + rightParts.get(rounds);
        afterPermitation.add(bitsOperations.doPermutation(IP_TRANSPOSITED, twoPartsAfterFunc));

        for (String str : afterPermitation) {
            encrypted.add(convertFromBaseToBase(str, 2, 16));
        }
    }

    private String doXorKnERn(int index) {
        String expandedR = expandByE(index - 1);//expanded by table E
        String Kn = keyN.get(index - 1); //got Kn
        xor = new Xor(Kn, expandedR);
        return xor.doXor();//returns XORed result: Kn + E(Rn-1)
    }

    private List<String> splitIntoB(String KnRn) {
        List<String> b = new ArrayList<>();
        int beginIndex = 0;
        int endIndex = 6;
        for (int i = 0; i < 8; i++) {
            b.add(KnRn.substring(beginIndex, endIndex));
            beginIndex += 6;
            endIndex += 6;
        }
        return b;
    }

    private String calcFunc(int index) {
        List<String> SbBlocks;
        String function = "";
        SbBlocks = calcSBox(splitIntoB(doXorKnERn(index)));
        bitsOperations = new BitsOperations();
        for (String str : SbBlocks) {
            function = function + str;
        }
        logger.debug(function + " func");
        return bitsOperations.doPermutation(P, function);
    }

    private String expandByE(int index) {
        BitsOperations bo = new BitsOperations();
        return bo.doPermutation(E, rightParts.get(index));
    }

    private List<String> calcSBox(List<String> b) {
        List<String> sFromB = new ArrayList<>();
        logger.debug("B - boxes: " + b);
        int i = 0;
        for (String bN : b) {
            sBoxe = new SBoxe(bN, i);
            sFromB.add(sBoxe.calculate());
            i++;
        }
        logger.debug("S(b): " + sFromB);
        return sFromB;
    }

    private List<String> createList(String part) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= rounds; i++) {
            if (i == 0) {
                list.add(part);
            } else
                list.add("");
        }
        return list;
    }

    private String convertFromBaseToBase(String str, int base1, int base2) {
        logger.info("str - " + str);
        BigInteger bi = new BigInteger(str, base1);
        logger.info(bi);
        return bi.toString(base2);
    }

    @Override
    public String toString() {
        return "Encrypt{" +
                "encrypted=" + encrypted.toString() +
                '}';
    }
}
