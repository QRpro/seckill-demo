package info.neilqin.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * @author Neil
 * @date Create in 0:42 2018/11/15
 */
@Data
public class GoodsSeckillPO {
    private Long id;
    private Long goodsId;
    private Float seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

}
