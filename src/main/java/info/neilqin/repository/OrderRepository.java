package info.neilqin.repository;

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

    @Insert("insert into sk_order(id,user_id, goods_id, goods_name, goods_count, goods_price,transaction_price, order_channel, status,is_seckill, create_date)values("
            + "#{id},#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice},#{transactionPrice}, #{orderChannel},#{status},#{isSeckill},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    void insert(OrderPO order);

}
