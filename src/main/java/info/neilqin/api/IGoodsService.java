package info.neilqin.api;

import info.neilqin.entity.vo.GoodsVO;
import java.util.List;

/**
 * @author Neil
 * @date Create in 1:05 2018/11/15
 */
public interface IGoodsService {
    /**
     * 获取所有商品信息
     * @return
     */
    List<GoodsVO> findAll();

    /**
     * 获取秒杀商品详情
     * @param goodsId
     * @return
     */
    GoodsVO getGoodsSeckillDetail(Long goodsId);

    /**
     * 减库存
     * @param goodsId
     * @return
     */
    boolean reduceStock(Long goodsId);
}
