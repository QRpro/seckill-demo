package info.neilqin.utils;

import java.security.MessageDigest;

/**
 * @author Neil
 * @date 2018/11/19 10:58
 */
public class EncryptUtils {

    public final static String MD5 = "MD5";

    public static String Md5Encrypt(String origin) {
        return Md5Encrypt(origin,"");
    }

    public static String Md5Encrypt(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance(MD5);
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }
    public static String saltEncrypt(String salt,String pwd){
        int length = salt.length();
        if (length == 0){return pwd;}
        StringBuffer sb = new StringBuffer();
        sb.append(salt.substring(0,salt.length()/2))
                .append(pwd).append(salt.substring(salt.length()/2));
        return sb.toString();
    }

    public static String getRandomSalt(int length){
        String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[length];
        for (int i = 0; i < rands.length; i++){
            int rand = (int) (Math.random() * a.length());
            rands[i] = a.charAt(rand);
        }
        return new String(rands);
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
