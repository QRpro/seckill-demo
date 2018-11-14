package info.neilqin.repository;

import info.neilqin.entity.po.OrderPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neil
 * @date Create in 0:52 2018/11/15
 */
@Repository
public interface OrderRepository extends CrudRepository<OrderPO,Long>{
}
