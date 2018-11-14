package info.neilqin.repository;

import info.neilqin.entity.po.GoodsPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neil
 * @date Create in 0:50 2018/11/15
 */
@Repository
public interface GoodsRepository extends CrudRepository<GoodsPO,Long>{
}
