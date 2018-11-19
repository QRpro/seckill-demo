package info.neilqin.repository;

import info.neilqin.entity.po.UserPO;
import java.util.Date;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Neil
 * @date Create in 0:53 2018/11/15
 */
@Mapper
public interface UserRepository{

    @Select("select * from sk_user where phone = #{phone}")
    UserPO getByPhone(String phone);

    @Insert("insert into sk_user(id,phone,nickname,password,salt,register_date)" +
            " values(#{id},#{phone},#{nickname},#{password},#{salt},#{registerDate})")
    void save(UserPO user);

    @Update("update sk_user set login_count = login_count+1,last_login_date=now() where id=#{id}")
    void recordLogin(Long id);
}
