package info.neilqin.api;

import info.neilqin.entity.po.UserPO;
import javax.servlet.http.HttpServletResponse; /**
 * @author Neil
 * @date Create in 1:04 2018/11/15
 */
public interface IUserService {

    /**
     * 登录
     * @param httpServletResponse
     * @param phone
     * @param pwd
     * @return
     */
    String login(HttpServletResponse httpServletResponse, String phone, String pwd);

    /**
     * 注册
     * @param phone
     * @param pwd
     * @param nickname
     */
    void signUp(String phone, String pwd,String nickname);

    /**
     * 获取token
     * @param response
     * @param token
     * @return
     */
    UserPO getByToken(HttpServletResponse response, String token);
}
