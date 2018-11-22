package info.neilqin.config;

import info.neilqin.api.IGoodsService;
import info.neilqin.common.constants.Constants;
import info.neilqin.entity.vo.GoodsVO;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Neil
 * @date 2018/11/21 16:14
 */
@Component
@Slf4j
public class InitConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    IGoodsService goodsService;
    @Autowired
    RedisTemplate redisTemplate;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        log.info(" start init redis storage.");
        List<GoodsVO> all = goodsService.findAll();
        for (GoodsVO goods : all) {
            this.redisTemplate.opsForValue().set(Constants.RedisKey.goodsStorageKey(goods.getGoodsId()), goods.getStockCount());
        }
        log.info(" end init redis storage.");
    }
}
