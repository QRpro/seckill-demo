package info.neilqin.common.constants;


/**
 * @author Neil
 * @date 2018/11/14 11:14
 */
public interface Constants {

    class Catch{
        public static final String JSON_REQUEST_BODY = "JSON_REQUEST_BODY";
        public static final String COOKIE_NAME_TOKEN = "TOKEN";
        public static final String REDIS_TOKEN_KEY = "TOKEN:";
        public static final String REDIS_USER_KEY = "USER:";
    }

    class RedisKey{
        public static String tokenKey(String token){
            return Catch.REDIS_TOKEN_KEY+token;
        }
        public static String userKey(Long id){
            return Catch.REDIS_USER_KEY+id;
        }
    }
}
