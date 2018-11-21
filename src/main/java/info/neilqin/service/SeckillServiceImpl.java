package info.neilqin.service;

import info.neilqin.api.IOrderService;
import info.neilqin.api.ISeckillService;
import info.neilqin.common.constants.Constants;
import info.neilqin.entity.po.OrderPO;
import info.neilqin.entity.po.UserPO;
import info.neilqin.exceptions.BusiException;
import info.neilqin.exceptions.GlobalException;
import info.neilqin.exceptions.ValidatorException;
import info.neilqin.helper.mq.MQSender;
import info.neilqin.helper.mq.SeckillMsg;
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
    @Autowired
    private IOrderService orderService;
    @Autowired
    private MQSender mqSender;

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
        if (obj == null){throw GlobalException.REQUEST_ILLEGAL;}
        if (!token.equals(obj.toString())){throw GlobalException.REQUEST_ILLEGAL;}
        //检查库存
        Long decrement = this.redisTemplate.opsForValue().decrement(Constants.RedisKey.goodsStorageKey(goodsId));
        if (decrement < 0){throw BusiException.SECKILL_OVER;}
        //是否重复秒杀
        OrderPO order = this.orderService.findSeckillOrderByUidAndGid(user.getId(),goodsId);
        if (order!=null){
            throw BusiException.SECKILL_REPEAT;
        }
        // 消息入队
        SeckillMsg msg = new SeckillMsg();
        msg.setGoodsId(goodsId);
        msg.setUser(user);
        mqSender.sendSeckillMsg(msg);
    }

    @Override
    public long getSeckillResult(Long id, Long goodsId) {
        OrderPO order = this.orderService.findSeckillOrderByUidAndGid(id, goodsId);
        if(order == null){
            //检查库存
            int num = (int) this.redisTemplate.opsForValue().get(Constants.RedisKey.goodsStorageKey(goodsId));
            if (num > 0){
                return Constants.SeckillStatus.SECKILL_INQUEUE;
            } else {
                return Constants.SeckillStatus.SECKILL_FAILED;
            }
        }
        return order.getId();
    }

}
