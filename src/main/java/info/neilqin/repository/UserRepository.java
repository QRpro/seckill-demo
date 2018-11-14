package info.neilqin.repository;

import info.neilqin.entity.po.UserPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neil
 * @date Create in 0:53 2018/11/15
 */
@Repository
public interface UserRepository extends CrudRepository<UserPO,Long>{
}
