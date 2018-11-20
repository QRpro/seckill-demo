package info.neilqin.utils;

import java.util.UUID;

/**
 * @author Neil
 * @date 2018/11/20 17:16
 */
public class RandomUtils {

    public static String UUID32(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static String UUID36(){
        return UUID.randomUUID().toString();
    }

}
