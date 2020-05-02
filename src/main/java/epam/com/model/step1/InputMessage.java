package epam.com.model.step1;

import epam.com.model.step1.BitsOperations;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputMessage {
    private byte[] plainText;
    private List<String> msgBlocks;
    private List<String> msgLeftParts;
    private List<String> msgRigthParts;
    private Scanner input;
    private BitsOperations bitsOp;

    public InputMessage() throws UnsupportedEncodingException {
        input = new Scanner(System.in);
        plainText = input.nextLine().getBytes("UTF-8");//Input msg
        msgBlocks = new ArrayList<>();
        bitsOp = new BitsOperations(plainText);
        setMsgBitsBlocks();
        splitIntoParts();
    }

    private void setMsgBitsBlocks() {
        bitsOp.fillMsgBlocks(msgBlocks);
    }

    private void splitIntoParts(){
        msgLeftParts = new ArrayList<>();
        msgRigthParts = new ArrayList<>();
        for (String e: msgBlocks) {
            msgLeftParts.add(e.substring(0,32));
            msgRigthParts.add(e.substring(32,64));
        }
    }

    public List<String> getMsgBlocks() {
        return msgBlocks;
    }

    public List<String> getMsgLeftParts() {
        return msgLeftParts;
    }

    public List<String> getMsgRigthParts() {
        return msgRigthParts;
    }

    public byte[] getPlainText() {
        return plainText;
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
