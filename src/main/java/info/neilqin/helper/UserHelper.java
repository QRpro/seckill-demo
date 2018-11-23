package info.neilqin.helper;

import info.neilqin.common.constants.Constants;
import info.neilqin.entity.po.UserPO;
import info.neilqin.utils.EncryptUtils;
import info.neilqin.utils.SnowFlake;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Neil
 * @date 2018/11/19 11:09
 */
@Component
public class UserHelper {

    private static final int SALT_LENGTH = 6;

    @Autowired
    RedisTemplate redisTemplate;

    public boolean checkPwd(String dbPwd, String salt, String pwd) {
        return dbPwd.equals(EncryptUtils.Md5Encrypt(EncryptUtils.saltEncrypt(salt, pwd)));
    }


    public void addCookie(HttpServletResponse httpServletResponse, String token,UserPO user) {
        int maxAge = 2*24*60*60;
        redisTemplate.opsForValue().set(Constants.RedisKey.tokenKey(token), user,maxAge, TimeUnit.SECONDS);
        Cookie cookie = new Cookie(Constants.Catch.COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    public UserPO createUser(String phone, String pwd,String nickName) {
        UserPO user = new UserPO();
        user.setId(SnowFlake.instance.nextId());
        String salt = EncryptUtils.getRandomSalt(SALT_LENGTH);
        user.setSalt(salt);
        String s = EncryptUtils.saltEncrypt(salt, pwd);
        user.setPassword(EncryptUtils.Md5Encrypt(s));
        user.setPhone(phone);
        user.setNickname(nickName);
        user.setRegisterDate(new Date());
        return user;
    }

    public UserPO getUserByToken(String token) {
        Object obj = this.redisTemplate.opsForValue().get(Constants.RedisKey.tokenKey(token));
        if (obj == null){return null;}
        return (UserPO) obj;
    }
}
