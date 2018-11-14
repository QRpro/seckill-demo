package info.neilqin.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Neil
 * @date Create in 0:46 2018/11/15
 */
@Entity
@Table(name = "sk_order_info")
@Data
public class OrderInfoPO {
    @Id
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
