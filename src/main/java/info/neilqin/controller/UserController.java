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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户相关Controller
 * @author Neil
 * @date 2018/11/15 10:18
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;
    @Autowired
    HttpServletResponse httpServletResponse;

    @GetMapping("/login")
    public String loginHome(UserPO user){
        // 已登录直接跳转列表页
        if (user!=null){
            return "redirect:/goods/list";
        }
        return "/login/login";
    }

    @PostMapping("/to_login")
    public JSONView<String> login(@JsonParam @Validator(ValidatorEnum.PHONE) String phone,
                                      @JsonParam @Validator(ValidatorEnum.NOT_BLANK) String pwd){
        String token = this.userService.login(httpServletResponse, phone, pwd);
        return JSONView.parseSuccess(token);
    }

    @PostMapping("/signup")
    public JSONView<ResultEnum> signUp(@JsonParam @Validator(ValidatorEnum.PHONE) String phone,
                                       @JsonParam @Validator(ValidatorEnum.NOT_BLANK) String pwd,
                                       @JsonParam @Validator(ValidatorEnum.NOT_BLANK)String nickname){
        this.userService.signUp(phone,pwd,nickname);
        return JSONView.parseCode(ResultEnum.SUCCESS);
    }

}
