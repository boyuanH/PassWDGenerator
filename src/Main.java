import boyuan.PWGenerator.PWGenerator;
import boyuan.PWGenerator.PWType;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String open = "ccb";
        String security = "SecurityCode";
        int length = 10;
        PWGenerator generator = new PWGenerator(open,security, PWType.NUMANDCAPITALSPE,length);
        String passwd = generator.getPassWD();
        generator.resetGenerator(PWType.ONLYNUM,6);
        passwd = generator.getPassWD();

    }

}
