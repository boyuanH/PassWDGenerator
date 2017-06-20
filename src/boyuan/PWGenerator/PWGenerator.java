package boyuan.PWGenerator;

import javax.print.DocFlavor;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by BoyuanHuang on 2017/6/18.
 */
public class PWGenerator {

    private String opencode;
    private String securityCode;
    private PWType pwType;
    private int length;


    private String passWD;

    public String getPassWD() {
        if(passWD == null || passWD==""){
            generatePassWD();
        }
        return passWD;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public PWGenerator(String opencode, String securityCode, PWType pwType, int length) {
        this.opencode = opencode;
        this.securityCode = securityCode;
        this.pwType = pwType;
        this.length = length;
    }

    private boolean sourceLegalCheck(){

        if(checkStringIsEmpty(opencode) || checkStringIsEmpty(securityCode)){
            return false;
        }

        if(pwType == null){
            pwType = PWType.ONLYNUM;
        }

        if(length <=0 || length > BasicValue.getMAXLength()){
            return false;
        }

        return true;
    }

    private boolean checkStringIsEmpty(String str){
        if(str == null || str.length()<=0){
            return true;
        }
        return false;
    }

    private void generatePassWD(){
        if(!sourceLegalCheck()){
            throw new NullPointerException();
        }

        String sourceString = opencode + BasicValue.getDEFAULTSTRING()+securityCode;
        switch (pwType){
            case ONLYNUM:
            {
                break;
            }
            case ONLYLOWER:
            {
                break;
            }
            case ONLYCAPITAL:
            {
                break;
            }
            case NUMANDLOWER:
            {
                break;
            }
            case NUMANDCAPITAL:
            {
                break;
            }
            case LOWERANDCAPITAL:
            {
                break;
            }
            case NUMLOWERCAPITAL:
            {
                break;
            }
            case NUMANDLOWERSPE:
            {
                break;
            }
            case NUMANDCAPITALSPE:
            {
                break;
            }
            case LOWERANDCAPITALSPE:
            {
                break;
            }
            case NUMLOWERCAPITALSPE:
            {
                break;
            }
        }

    }

    private String replaceHEXValueToSpecialSequence(String sourceString){
        String numberSequence = replaceHEXValueToNumberSequenceByEasyReplace(sourceString);
        StringBuffer sbuffer = new StringBuffer();
        for(int i = 0;i<numberSequence.length();i++){
            sbuffer.append(BasicValue.getSPECHARACTERSEQUENCE().charAt(Integer.valueOf(numberSequence.charAt(i))));
        }
        return sbuffer.toString();
    }

    private String replaceHEXValueToNumberSequenceByEasyReplace(String sourceString){
        if(sourceString == null || sourceString.length()<=0){
            throw new IllegalArgumentException();
        }

        if(!isStringOnlyHEX(sourceString)){
            throw new IllegalArgumentException();
        }

        sourceString = sourceString.toUpperCase();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0;i<sourceString.length();i++){
            if(BasicValue.getNUMBERSEQUENCE().indexOf(sourceString.charAt(i)) != -1){
                // 非数字
                switch (String.valueOf(sourceString.charAt(i))){
                    case "A":
                        stringBuffer.append("10");
                        break;
                    case "B":
                        stringBuffer.append("32");
                        break;
                    case "C":
                        stringBuffer.append("54");
                        break;
                    case "D":
                        stringBuffer.append("76");
                        break;
                    case "E":
                        stringBuffer.append("98");
                        break;
                    case "F":
                        stringBuffer.append("05");
                        break;
                    default:
                        break;
                }
            }else {
                stringBuffer.append(sourceString.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    private String replaceHEXValueToLowerSequenceByEasyReplace(String sourceString){
        return  replaceHEXValueToCapitalSequenceByEasyReplace(sourceString).toLowerCase();
    }

    private String replaceHEXValueToCapitalSequenceByEasyReplace(String sourceString){
        if(sourceString == null || sourceString.length()<=0){
            throw new IllegalArgumentException();
        }
        if(!isStringOnlyHEX(sourceString)){
            throw new IllegalArgumentException();
        }

        String currentSource = sourceString;

        StringBuffer stringBuffer = new StringBuffer();
        do{
            String[] dividedStrings = divideStringToNumberAndOther(currentSource);
            stringBuffer.append(dividedStrings[0]);
            currentSource = generateMD5WithRandomCapitalAndLowerLetter(dividedStrings[1],true);
        }while (stringBuffer.length() < 64);

        String fullNumberSequence = stringBuffer.substring(0,63).toString();
        System.out.println(fullNumberSequence);
        StringBuffer sBufferSequenceOut = new StringBuffer();
        for(int i = 0;i<64;i++){
            int frontInt = Integer.valueOf(fullNumberSequence.charAt(i));
            i++;
            int backInt  = Integer.valueOf(fullNumberSequence.charAt(i));
            int indexNum=0;
            switch (frontInt){
                case 0:
                case 3:
                case 6:
                case 9:
                {
                    switch (backInt){
                        case 0:
                        case 1:
                            indexNum = 20;
                            break;
                        case 2:
                        case 3:
                            indexNum = 21;
                            break;
                        case 4:
                            indexNum = 22;
                            break;
                        case 5:
                            indexNum = 23;
                            break;
                        case 6:
                        case 7:
                            indexNum = 24;
                            break;
                        case 8:
                        case 9:
                            indexNum = 25;
                            break;
                    }
                    break;
                }
                case 1:
                case 4:
                case 7:
                {
                    indexNum = backInt;
                    break;
                }
                case 2:
                case 5:
                case 8:
                {
                    indexNum = 10+backInt;
                    break;
                }
            }
            sBufferSequenceOut.append(BasicValue.getCAPITALLETTERSEQUERNCE().charAt(indexNum));
        }

        return sBufferSequenceOut.toString();
    }

    private String[] divideStringToNumberAndOther(String sourceString){
        String[] returnValues = new String[2];
        StringBuffer sbNumber = new StringBuffer();
        StringBuffer sbLetter = new StringBuffer();
        for(int i = 0;i<sourceString.length();i++){
            if(BasicValue.getNUMBERSEQUENCE().indexOf(String.valueOf(sourceString.charAt(i))) != -1){
                sbNumber.append(String.valueOf(sourceString.charAt(i)));
            }else {
                if((BasicValue.getCAPITALLETTERSEQUERNCE().indexOf(String.valueOf(sourceString.charAt(i))) == -1) && (BasicValue.getLOWERCASESEQUENCE().indexOf(String.valueOf(sourceString.charAt(i))) == -1)){
                    continue;
                }
                sbLetter.append(String.valueOf(sourceString.charAt(i)));
            }
        }
        returnValues[0] = sbNumber.toString();
        if(sbLetter.length()>0){
            returnValues[1] = sbLetter.toString();
        }else {
            returnValues[1] = returnValues[0];
        }
        return  returnValues;
    }

    private boolean isStringOnlyHEX(String sourceString){
        if(sourceString == null || sourceString.length()<=0){
            throw new IllegalArgumentException();
        }
        sourceString = sourceString.toUpperCase();
        for(int i = 0;i<sourceString.length();i++){

            if(!isSubString(String.valueOf(sourceString.charAt(i)),BasicValue.getHEXCAPITALSTRING())){
                return false;
            }
        }
        return true;
    }

    private boolean isSubString(String sourceString,String targetString){
        if(sourceString == null || sourceString.length()<=0){
            throw new IllegalArgumentException();
        }
        if(targetString == null || targetString.length()<=0){
            throw new IllegalArgumentException();
        }

        if(targetString.indexOf(sourceString) != -1){
            return true;
        }else {
            return  false;
        }
    }

    private String generateMD5WithRandomCapitalAndLowerLetter(String sourceStr,boolean isonlyCapital){
        String destStr = "";
        if(sourceStr == null || sourceStr.length()<=0){
            throw new IllegalArgumentException();
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            destStr = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            destStr = "";
            throw new NullPointerException("MD5加密出现错误");
        }
        finally {
            if(isonlyCapital == false){
                StringBuffer sbuffer = new StringBuffer();
                Random r = new Random();
                for(int i = 0;i<destStr.length();i++){
                    if(!isSubString(String.valueOf(destStr.charAt(i)),BasicValue.getNUMBERSEQUENCE())){
                        int ranNumber = r.nextInt(2);
                        if(ranNumber < 1){
                            sbuffer.append(String.valueOf(destStr.charAt(i)).toLowerCase());
                        }else {
                            sbuffer.append(String.valueOf(destStr.charAt(i)).toUpperCase());
                        }
                    }else {
                        sbuffer.append(String.valueOf(destStr.charAt(i)));
                    }
                }
                destStr = sbuffer.toString();
            }

        }
        return destStr;
    }





}
