package epam.com.model.step2;

public class Xor {
    private String firstStr;
    private String secondStr;

    public Xor(String firstStr, String secondStr) {
        this.firstStr = firstStr;
        this.secondStr = secondStr;
    }

    public String doXor() {
        byte[] firstArr = firstStr.getBytes();
        byte[] secondArr = secondStr.getBytes();
        byte[] result = new byte[firstArr.length];
        String resultString = "";
        for (int i = 0; i < firstArr.length; i++) {
            result[i] = (byte) (firstArr[i] ^ secondArr[i % secondArr.length]);
        }
        for (byte b : result) {
            resultString += Integer.toBinaryString(b);
        }
        return resultString;
    }

}
