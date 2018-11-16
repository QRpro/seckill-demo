package info.neilqin.entity.vo;

import info.neilqin.entity.po.UserPO;
import lombok.Data;

/**
 * Created by jiangyunxiong on 2018/5/24.
 */
@Data
public class GoodsDetailVO {
    private int seckillStatus = 0;
    private int remainSeconds = 0;
    private GoodsVO goods ;
    private UserPO user;
}
