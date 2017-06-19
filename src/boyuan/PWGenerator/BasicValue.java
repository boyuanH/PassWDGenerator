package boyuan.PWGenerator;

/**
 * Created by BoyuanHuang on 2017/6/18.
 */
class BasicValue {
    private static final String DEFAULTSTRING           = "HBYlike000";
    private static final String NUMBERSEQUENCE          = "0123456789";
    private static final String LOWERCASESEQUENCE       = "nopqrstuvwabcdefghijklmxyz";
    private static final String CAPITALLETTERSEQUERNCE  = "NOPQRSTUVWABCDEFGHIJKLMXYZ";
    //  0,3,6,9 ->2  1,4,7->0  2,5,8->1
    private static final String SPECHARACTERSEQUENCE    = "!#$%&*+-?=";
    private static final String HEXCAPITALSTRING        = "0123456789ABCDEF";

    public static String getHEXCAPITALSTRING() {
        return HEXCAPITALSTRING;
    }

    public static String getDEFAULTSTRING() { return DEFAULTSTRING;}

    public static String getNUMBERSEQUENCE() {
        return NUMBERSEQUENCE;
    }

    public static String getLOWERCASESEQUENCE() {
        return LOWERCASESEQUENCE;
    }

    public static String getCAPITALLETTERSEQUERNCE() {
        return CAPITALLETTERSEQUERNCE;
    }

    public static String getSPECHARACTERSEQUENCE() {
        return SPECHARACTERSEQUENCE;
    }
}
