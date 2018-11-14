package info.neilqin.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Neil
 * @date Create in 23:53 2018/11/14
 */
@Entity
@Table(name = "sk_user")
@Data
public class UserPO {

    @Id
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
