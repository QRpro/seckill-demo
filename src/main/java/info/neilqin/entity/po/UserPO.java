package info.neilqin.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * @author Neil
 * @date Create in 23:53 2018/11/14
 */
@Data
public class UserPO {

    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
