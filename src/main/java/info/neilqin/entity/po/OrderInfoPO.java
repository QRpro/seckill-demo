package info.neilqin.entity.po;

import java.util.Date;
import lombok.Data;

/**
 * @author Neil
 * @date Create in 0:46 2018/11/15
 */
@Data
public class OrderInfoPO {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private String goodsCount;
    private Float goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
