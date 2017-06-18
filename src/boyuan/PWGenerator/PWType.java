package boyuan.PWGenerator;

/**
 * Created by BoyuanHuang on 2017/6/18.
 */
public enum PWType {
    ONLYNUM,                //数字
    ONLYLOWER,              //小写字母
    ONLYCAPITAL,            //大写字母
    NUMANDLOWER,            //数字与小写字母
    NUMANDCAPITAL,          //数字与大写字母
    LOWERANDCAPITAL,        //小写字母与大写字母
    NUMLOWERCAPITAL,        //数字小写和大写字母
    NUMANDLOWERSPE,         //数字小写和符号
    NUMANDCAPITALSPE,       //数字大写和符号
    LOWERANDCAPITALSPE,     //小写大写和符号
    NUMLOWERCAPITALSPE;     //数字小写大写和符号

}
