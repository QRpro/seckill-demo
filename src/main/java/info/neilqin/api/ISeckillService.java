package info.neilqin.api;

import info.neilqin.entity.po.UserPO;

/**
 * @author Neil
 * @date 2018/11/20 17:12
 */
public interface ISeckillService {

    /**
     * 获取秒杀token
     * @param goodsId
     * @param user
     * @return
     */
    String getSeckillPath(Long goodsId, UserPO user);

    /**
     * 秒杀校验及入队
     * @param token
     * @param goodsId
     * @param user
     */
    void seckill(String token, Long goodsId, UserPO user);

    /**
     * 获取秒杀结果
     * @param id
     * @param goodsId
     * @return
     */
    long getSeckillResult(Long id, Long goodsId);
}
