package info.neilqin.entity.po;

import lombok.Data;

/**
 * @author Neil
 * @date Create in 0:44 2018/11/15
 */
@Data
public class OrderPO {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
