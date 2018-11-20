package info.neilqin.repository;

import info.neilqin.entity.po.OrderInfoPO;
import info.neilqin.entity.po.OrderPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @author Neil
 * @date Create in 0:52 2018/11/15
 */
@Mapper
public interface OrderRepository {

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    void insert(OrderInfoPO orderInfo);

    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    void insertSeckillOrder(OrderPO seckillOrder);
}
