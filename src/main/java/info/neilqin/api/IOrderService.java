package info.neilqin.api;

import info.neilqin.entity.po.OrderPO;
import info.neilqin.entity.po.UserPO;
import info.neilqin.entity.vo.GoodsVO;

/**
 * @author Neil
 * @date Create in 0:07 2018/11/21
 */
public interface IOrderService {

    OrderPO findSeckillOrderByUidAndGid(Long id, Long goodsId);

    void seckill(UserPO user, GoodsVO goods);
}
