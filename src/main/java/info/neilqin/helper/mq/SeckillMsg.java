package info.neilqin.helper.mq;

import info.neilqin.entity.po.UserPO;
import lombok.Data;

/**
 * 秒杀消息
 * @author Neil
 * @date Create in 0:43 2018/11/21
 */
@Data
public class SeckillMsg {

    private UserPO user;
    private long goodsId;
}
