package info.neilqin.repository;

import info.neilqin.entity.po.GoodsSeckillPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neil
 * @date Create in 0:51 2018/11/15
 */
@Repository
public interface GSeckillRepository extends CrudRepository<GoodsSeckillPO,Long>{
}
