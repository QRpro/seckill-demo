package info.neilqin.repository;

import info.neilqin.entity.po.GoodsPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Neil
 * @date Create in 0:50 2018/11/15
 */
@Repository
public class GoodsRepository extends BaseDaoImpl<GoodsPO> implements GoodsDao{


    @Override
    public List<GoodsPO> getAll() {
        return null;
    }
}
