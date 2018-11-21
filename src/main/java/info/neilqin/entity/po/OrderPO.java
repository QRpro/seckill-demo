package info.neilqin.entity.po;

import java.util.Date;
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
    private Long deliveryAddrId;
    private Float transactionPrice;
    private Integer isSeckill;
    private String goodsName;
    private Integer goodsCount;
    private Float goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
