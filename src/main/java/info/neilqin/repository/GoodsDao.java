package info.neilqin.repository;

import info.neilqin.entity.po.GoodsPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Neil
 * @description: TODO
 * @date Create in 2:03 2018/11/16
 */
@Mapper
public interface GoodsDao extends BaseDao<GoodsPO>{
    @Select("select * from sk_goods")
    List<GoodsPO> getAll();
}
