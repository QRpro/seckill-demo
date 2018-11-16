package info.neilqin.entity.vo;

import info.neilqin.entity.po.GoodsPO;
import java.util.Date;
import lombok.Data;

/**
 * @author Neil
 * @date 2018/11/16 11:34
 */
@Data
public class GoodsVO extends GoodsPO{

    private Long goodsId;
    private Float seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
