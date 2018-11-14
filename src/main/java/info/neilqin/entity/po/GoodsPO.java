package info.neilqin.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Neil
 * @date Create in 0:38 2018/11/15
 */
@Table(name = "sk_user")
@Entity
@Data
public class GoodsPO {
    @Id
    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private String goodsPrice;
    private String goodsStock;
}
