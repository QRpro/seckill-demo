package info.neilqin.common.constants;


/**
 * @author Neil
 * @date 2018/11/14 11:14
 */
public interface Constants {

    /**
     * 缓存相关常量
     */
    class Catch{
        public static final String JSON_REQUEST_BODY = "JSON_REQUEST_BODY";
        public static final String COOKIE_NAME_TOKEN = "TOKEN";
        public static final String REDIS_TOKEN_KEY = "TOKEN:%s";
        public static final String REDIS_USER_KEY = "USER:%s";
        public static final String REDIS_SECKILL_TOKEN = "SECKILL_TOKEN:%s";
        public static final String REDIS_GOODS_STORAGE = "GOODS_STORAGE:%s";
        public static final String REDIS_SECKILL_RECORD = "SECKILL_ORDER:%s";
    }

    /**
     * Queue相关常量
     */
    class Queue{
        public static final String SECKILL_QUEUE = "SECKILL.QUEUE";
    }

    /**
     * 秒杀状态
     */
    class SeckillStatus{
        public static final int SECKILL_FAILED = -1;
        public static final int SECKILL_SUCCESS = 1;
        public static final int SECKILL_INQUEUE = 0;
    }

    /**
     * 获取redis Key
     */
    class RedisKey{
        /** 用户登录TOKEN key */
        public static String tokenKey(String token){
            return String.format(Catch.REDIS_TOKEN_KEY,token);
        }
        /** 秒杀path key */
        public static String seckillTokenKey(Long userId,Long goodsId){
            return String.format(Catch.REDIS_SECKILL_TOKEN,userId+"_"+goodsId);
        }
        /** 商品库存 key */
        public static String goodsStorageKey(Long goodsId){
            return String.format(Catch.REDIS_GOODS_STORAGE,goodsId.toString());
        }
        /** 秒杀Order key */
        public static String seckillOrderKey(Long userId,Long goodsId){
            return String.format(Catch.REDIS_SECKILL_RECORD,userId+"_"+goodsId);
        }
    }

    class HttpMethod{
        public static final String GET = "GET";
        public static final String POST = "POST";
    }
}
