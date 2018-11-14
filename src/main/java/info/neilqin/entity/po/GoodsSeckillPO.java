package info.neilqin.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Neil
 * @date Create in 0:42 2018/11/15
 */
@Table(name = "sk_goods_seckill")
@Entity
@Data
public class GoodsSeckillPO {
    @Id
    private Long id;
    private Long goodsId;
    private Float seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
