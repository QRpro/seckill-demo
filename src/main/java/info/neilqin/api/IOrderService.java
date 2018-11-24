package info.neilqin.api;

import info.neilqin.entity.po.OrderPO;
import info.neilqin.entity.po.UserPO;
import info.neilqin.entity.vo.GoodsVO;

/**
 * @author Neil
 * @date Create in 0:07 2018/11/21
 */
public interface IOrderService {

    /**
     * 从redis获取秒杀记录
     * @param id
     * @param goodsId
     * @return
     */
    OrderPO findSeckillOrderByUidAndGid(Long id, Long goodsId);

    /**
     * 秒杀逻辑
     * @param user
     * @param goods
     */
    void seckillByBD(UserPO user, GoodsVO goods);
}
