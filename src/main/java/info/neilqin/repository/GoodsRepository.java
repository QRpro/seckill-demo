package info.neilqin.repository;

import info.neilqin.entity.po.GoodsPO;
import info.neilqin.entity.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Neil
 * @date Create in 2:03 2018/11/16
 */
@Mapper
public interface GoodsRepository{
    @Select("select * from sk_goods")
    List<GoodsPO> getAll();

    @Select("select gs.id,gs.goods_id ,gs.seckill_price ,gs.stock_count,gs.start_date,gs.end_date,g.goods_name,g.goods_title,g.goods_img,g.goods_detail,g.goods_price from sk_goods_seckill gs,sk_goods g where gs.goods_id = g.id")
    List<GoodsVO> getGoodsSkillInfo();
    @Select("select gs.id,gs.goods_id ,gs.seckill_price ,gs.stock_count,gs.start_date,gs.end_date,g.goods_name,g.goods_title,g.goods_img,g.goods_detail,g.goods_price from sk_goods_seckill gs,sk_goods g where gs.goods_id = g.id and gs.goods_id=#{goodsId}")
    GoodsVO getGoodsSkillInfoByGoodsId(@Param("goodsId") Long goodsId);
}
