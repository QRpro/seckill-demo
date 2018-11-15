package info.neilqin.controller;

import info.neilqin.anno.JsonParam;
import info.neilqin.anno.Validator;
import info.neilqin.common.enums.ResultEnum;
import info.neilqin.common.enums.ValidatorEnum;
import info.neilqin.common.views.JSONView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Neil
 * @date 2018/11/15 10:18
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @RequestMapping("/login")
    public String loginHome(){
        return "/login/login";
    }

    @RequestMapping("/to_login")
    public JSONView<ResultEnum> login(@JsonParam @Validator(ValidatorEnum.PHONE) String phone,
                                      @JsonParam @Validator(ValidatorEnum.NOT_BLANK) String pwd){
        System.out.println(phone+":"+pwd);
        return JSONView.parseSuccess(ResultEnum.SUCCESS);
    }
}
