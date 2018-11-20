package info.neilqin.common.constants;


/**
 * @author Neil
 * @date 2018/11/14 11:14
 */
public interface Constants {

    class Catch{
        public static final String JSON_REQUEST_BODY = "JSON_REQUEST_BODY";
        public static final String COOKIE_NAME_TOKEN = "TOKEN";
        public static final String REDIS_TOKEN_KEY = "TOKEN:%s";
        public static final String REDIS_USER_KEY = "USER:%s";
        public static final String REDIS_SECKILL_TOKEN = "SECKILL_TOKEN:%s";
    }

    class RedisKey{
        public static String tokenKey(String token){
            return String.format(Catch.REDIS_TOKEN_KEY,token);
        }
        public static String userKey(Long id){
            return String.format(Catch.REDIS_USER_KEY,id);
        }
        public static String seckillTokenKey(Long userId,Long goodsId){
            return String.format(Catch.REDIS_SECKILL_TOKEN,userId+"_"+goodsId);
        }

    }
}
