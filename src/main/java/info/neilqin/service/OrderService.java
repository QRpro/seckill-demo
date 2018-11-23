package info.neilqin.service;

import info.neilqin.api.IGoodsService;
import info.neilqin.api.IOrderService;
import info.neilqin.common.constants.Constants;
import info.neilqin.entity.po.OrderPO;
import info.neilqin.entity.po.UserPO;
import info.neilqin.entity.vo.GoodsVO;
import info.neilqin.repository.OrderRepository;
import info.neilqin.utils.SnowFlake;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Neil
 * @date Create in 0:08 2018/11/21
 */
@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RedisTemplate<String,OrderPO> redisTemplate;
    @Autowired
    IGoodsService goodsService;

    @Override
    public OrderPO findSeckillOrderByUidAndGid(Long id, Long goodsId) {
        return this.redisTemplate.opsForValue().get(Constants.RedisKey.seckillOrderKey(id, goodsId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void seckill(UserPO user, GoodsVO goods) {
        // 减库存
        boolean success = goodsService.reduceStock(goods.getGoodsId());
        if (!success){return;}
        // 下订单
        OrderPO order = this.createNewOrder(user, goods);
        redisTemplate.opsForValue().set(Constants.RedisKey.seckillOrderKey(user.getId(),goods.getGoodsId()), order);
    }

    private OrderPO createNewOrder(UserPO user, GoodsVO goods) {
        OrderPO order = new OrderPO();
        order.setCreateDate(new Date());
        order.setDeliveryAddrId(0L);
        order.setGoodsCount(1);
        order.setGoodsId(goods.getId());
        order.setGoodsName(goods.getGoodsName());
        order.setGoodsPrice(goods.getGoodsPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setUserId(user.getId());
        order.setIsSeckill(1);
        order.setTransactionPrice(goods.getSeckillPrice());
        order.setId(SnowFlake.instance.nextId());
        this.orderRepository.insert(order);
        return order;
    }
}
