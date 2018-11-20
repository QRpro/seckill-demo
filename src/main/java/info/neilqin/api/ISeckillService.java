package info.neilqin.api;

import info.neilqin.entity.po.UserPO; /**
 * @author Neil
 * @date 2018/11/20 17:12
 */
public interface ISeckillService {

    String getSeckillPath(Long goodsId, UserPO user);

    void seckill(String token, Long goodsId, UserPO user);
}
