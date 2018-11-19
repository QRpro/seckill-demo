package info.neilqin.service;

import info.neilqin.api.IUserService;
import info.neilqin.entity.po.UserPO;
import info.neilqin.exceptions.ValidatorException;
import info.neilqin.helper.UserHelper;
import info.neilqin.repository.UserRepository;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neil
 * @date Create in 1:05 2018/11/15
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserHelper userHelper;

    @Override
    public void login(HttpServletResponse httpServletResponse, String phone, String pwd) {
        UserPO user = this.userRepository.getByPhone(phone);
        if(null == user){throw ValidatorException.USER_NOT_EXIST;}
        String dbPwd = user.getPassword();
        String salt = user.getSalt();
        boolean canLogin = this.userHelper.checkPwd(dbPwd,salt,pwd);
        if (!canLogin){throw ValidatorException.PWD_ERR;}
        String token = UUID.randomUUID().toString();
        this.userRepository.recordLogin(user.getId());
        this.userHelper.addCookie(httpServletResponse,token,user);
    }

    @Override
    public void signUp(String phone, String pwd,String nickname) {
        UserPO user = this.userRepository.getByPhone(phone);
        if(user!=null){throw ValidatorException.ID_EXISTS;}
        UserPO newUser = this.userHelper.createUser(phone,pwd,nickname);
        this.userRepository.save(newUser);
    }

    @Override
    public UserPO getByToken(HttpServletResponse response, String token) {
        UserPO user = this.userHelper.getUserByToken(token);
        if (user == null) {return null;}
        this.userHelper.addCookie(response, token, user);
        return user;
    }

}
