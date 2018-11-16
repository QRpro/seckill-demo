package info.neilqin.service;

import info.neilqin.api.IGoodsService;
import info.neilqin.entity.vo.GoodsVO;
import info.neilqin.repository.GoodsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neil
 * @date Create in 1:06 2018/11/15
 */
@Service
public class GoodsServiceImpl implements IGoodsService{

    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public List<GoodsVO> findAll() {
       return this.goodsRepository.getGoodsSkillInfo();
    }

    @Override
    public GoodsVO getGoodsSeckillDetail(Long goodsId) {
        return this.goodsRepository.getGoodsSkillInfoByGoodsId(goodsId);
    }
}
