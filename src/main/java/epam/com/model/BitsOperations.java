package epam.com.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BitsOperations implements Operable{
    static final int COUNT_OF_BLOCKS = 8;
    static final int KEY_OF_GAP = 32;
    private byte[] plainText;

    public BitsOperations(byte[] plainText) {
        this.plainText = plainText;
    }

    public void fillMsgBlocks(List<String> msgBlocks) {
        List<String> msgSymbols = new ArrayList<>();
        msgSymbols.addAll(getBitsList());
        String str = "";

        for (int i = 0; i < msgSymbols.size(); i++) {
            str = str + msgSymbols.get(i);
            if ((i + 1) % 8 == 0) {
                msgBlocks.add(str);
                str = "";
            }
        }
    }


    private List<String> getBitsList() {
        List<String> fullBitsLine = new ArrayList();
        String beginningZeros;
        String inputLine;

        for (byte b : plainText) {
            inputLine = Integer.toBinaryString(b);
            beginningZeros = "";
            for (int i = 0; i < 8 - inputLine.length(); i++) {
                beginningZeros += "0";
            }
            fullBitsLine.add(beginningZeros + inputLine);
        }
        return fillEndMissingBits(fullBitsLine);
    }

    private List<String> fillEndMissingBits(List<String> list) {
        for (int i = 1; i <= getInsufficiency(); i++) {
            list.add("00" + Integer.toBinaryString(KEY_OF_GAP));
        }
        return list;
    }

    private int getInsufficiency() {
        int counter = 0;
        for (byte e : plainText) {
            counter++;
            if (counter == 8) {
                counter = 0;
            }
        }
        return COUNT_OF_BLOCKS - counter;
    }
}
