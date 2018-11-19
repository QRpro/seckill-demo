package info.neilqin.api;

import info.neilqin.entity.po.UserPO;
import javax.servlet.http.HttpServletResponse; /**
 * @author Neil
 * @date Create in 1:04 2018/11/15
 */
public interface IUserService {

    void login(HttpServletResponse httpServletResponse, String phone, String pwd);

    void signUp(String phone, String pwd,String nickname);

    UserPO getByToken(HttpServletResponse response, String token);
}
