package info.neilqin.api;

import info.neilqin.entity.vo.GoodsVO;
import java.util.List;

/**
 * @author Neil
 * @date Create in 1:05 2018/11/15
 */
public interface IGoodsService {
    List<GoodsVO> findAll();

    GoodsVO getGoodsSeckillDetail(Long goodsId);
}
