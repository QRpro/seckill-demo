package info.neilqin.service;

import info.neilqin.api.IGoodsService;
import info.neilqin.api.IOrderService;
import info.neilqin.common.constants.Constants;
import info.neilqin.entity.po.OrderInfoPO;
import info.neilqin.entity.po.OrderPO;
import info.neilqin.entity.po.UserPO;
import info.neilqin.entity.vo.GoodsVO;
import info.neilqin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Neil
 * @date Create in 0:08 2018/11/21
 */
@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    IGoodsService goodsService;

    @Override
    public OrderPO findSeckillOrderByUidAndGid(Long id, Long goodsId) {
        Object obj = this.redisTemplate.opsForValue().get(Constants.RedisKey.seckillTokenKey(id, goodsId));
        if (obj == null){ return null;}
        else{return (OrderPO) obj;}
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void seckill(UserPO user, GoodsVO goods) {
        // 减库存
        boolean success = goodsService.reduceStock(goods.getGoodsId());
        if (!success){return;}
        // 下订单
        this.createNewOrder(user,goods);
    }

    private void createNewOrder(UserPO user, GoodsVO goods) {
        OrderInfoPO orderInfo = new OrderInfoPO();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        this.orderRepository.insert(orderInfo);
        OrderPO seckillOrder = new OrderPO();
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setUserId(user.getId());
        this.orderRepository.insertSeckillOrder(seckillOrder);
        redisTemplate.opsForValue().set(Constants.RedisKey.seckillOrderKey(user.getId(),goods.getGoodsId()), seckillOrder);
    }
}
