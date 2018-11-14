package info.neilqin.repository;

import info.neilqin.entity.po.OrderInfoPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neil
 * @date Create in 0:49 2018/11/15
 */
@Repository
public interface OrderInfoRepository extends CrudRepository<OrderInfoPO,Long> {
}
