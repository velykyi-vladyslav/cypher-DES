package epam.com.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputMessage {
    Scanner input;
    byte[] plainText;
    private List<String> msgBlocks;
    private List<String> msgLeftParts;
    private List<String> msgRigthParts;

    BitsOperations bitsOp;

    public InputMessage() throws UnsupportedEncodingException {
        input = new Scanner(System.in);
        plainText = input.nextLine().getBytes("UTF-8");
        msgBlocks = new ArrayList<>();
        msgLeftParts = new ArrayList<>();
        msgRigthParts = new ArrayList<>();
        bitsOp = new BitsOperations(plainText);
    }

    public void setMsgBitsBlocks() {
        bitsOp.fillMsgBlocks(msgBlocks);
    }
    public void splitIntoParts(){
        for (String e: msgBlocks) {
            msgLeftParts.add(e.substring(0,32));
            msgRigthParts.add(e.substring(32,64));
        }
    }

    @Override
    public String toString() {
        return "InputMessage{" +
                "msgBlocks=" + msgBlocks.toString() +
                ", msgLeftParts=" + msgLeftParts.toString() +
                ", msgRigthParts=" + msgRigthParts.toString() +
                '}';
    }
}
