package info.neilqin.service;

import info.neilqin.api.ISeckillService;
import info.neilqin.common.constants.Constants;
import info.neilqin.entity.po.UserPO;
import info.neilqin.exceptions.BusiException;
import info.neilqin.utils.RandomUtils;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Neil
 * @date 2018/11/20 17:12
 */
@Service
public class SeckillServiceImpl implements ISeckillService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getSeckillPath(Long goodsId, UserPO user) {
        String uuid = RandomUtils.UUID32();
        this.redisTemplate.opsForValue().set(Constants.RedisKey.seckillTokenKey(user.getId(), goodsId), uuid,10, TimeUnit.SECONDS);
        return uuid;
    }

    @Override
    public void seckill(String token, Long goodsId, UserPO user) {
        //验证token
        Object obj = this.redisTemplate.opsForValue().get(Constants.RedisKey.seckillTokenKey(user.getId(), goodsId));
        if (obj == null){throw BusiException.SECKILL_FAILED;}
        if (!token.equals(obj.toString())){throw BusiException.SECKILL_FAILED;}
        //检查库存

    }

}
