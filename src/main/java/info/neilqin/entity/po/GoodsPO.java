package info.neilqin.entity.po;

import lombok.Data;

/**
 * @author Neil
 * @date Create in 0:38 2018/11/15
 */
@Data
public class GoodsPO {
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Float goodsPrice;
    private Integer goodsStock;
}
