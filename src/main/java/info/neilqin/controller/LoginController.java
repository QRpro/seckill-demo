package info.neilqin.controller;

import info.neilqin.anno.JsonParam;
import info.neilqin.anno.Validator;
import info.neilqin.api.IUserService;
import info.neilqin.common.enums.ResultEnum;
import info.neilqin.common.enums.ValidatorEnum;
import info.neilqin.common.views.JSONView;
import info.neilqin.entity.po.UserPO;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Neil
 * @date 2018/11/15 10:18
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    IUserService userService;
    @Autowired
    HttpServletResponse httpServletResponse;

    @RequestMapping("/login")
    public String loginHome(UserPO user){
        if (user!=null){
            return "redirect:/goods/list";
        }
        return "/login/login";
    }

    @RequestMapping("/to_login")
    public JSONView<ResultEnum> login(@JsonParam @Validator(ValidatorEnum.PHONE) String phone,
                                      @JsonParam @Validator(ValidatorEnum.NOT_BLANK) String pwd){
        this.userService.login(httpServletResponse,phone,pwd);
        return JSONView.parseSuccess(ResultEnum.SUCCESS);
    }

    @RequestMapping("/signup")
    public JSONView<ResultEnum> signUp(@JsonParam @Validator(ValidatorEnum.PHONE) String phone,
                                       @JsonParam @Validator(ValidatorEnum.NOT_BLANK) String pwd,
                                       @JsonParam @Validator(ValidatorEnum.NOT_BLANK)String nickname){
        this.userService.signUp(phone,pwd,nickname);
        return JSONView.parseCode(ResultEnum.SUCCESS);
    }

}
