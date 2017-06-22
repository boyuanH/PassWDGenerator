import boyuan.PWGenerator.PWGenerator;
import boyuan.PWGenerator.PWType;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        String open = "ccb";
        //String security = UUID.randomUUID().toString();
        String security = "d72a12763af0458f9cdb45dd61cdba2c";

        int length = 10;
        long Time1=System.currentTimeMillis();
        PWGenerator generator = new PWGenerator(open,security, PWType.NUMANDCAPITALSPE,length);
        long Time2=System.currentTimeMillis();
        String passwd1 = generator.getPassWD();
        long Time3=System.currentTimeMillis();
        generator.resetGenerator(PWType.ONLYNUM,6);
        long Time4=System.currentTimeMillis();
        String passwd2 = generator.getPassWD();
        long Time5=System.currentTimeMillis();

        System.out.println(passwd1);
        System.out.println(passwd2);
        System.out.println(Time2-Time1);
        System.out.println(Time3-Time2);
        System.out.println(Time5-Time4);



    }

}
