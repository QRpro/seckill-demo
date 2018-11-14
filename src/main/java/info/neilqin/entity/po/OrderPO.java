package info.neilqin.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Neil
 * @date Create in 0:44 2018/11/15
 */
@Entity
@Table(name = "sk_order")
@Data
public class OrderPO {
    @Id
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;
}
