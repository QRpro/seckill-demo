package info.neilqin.controller;

import info.neilqin.anno.JsonParam;
import info.neilqin.common.enums.ResultEnum;
import info.neilqin.common.views.JSONView;
import info.neilqin.entity.po.GoodsPO;
import info.neilqin.entity.po.UserPO;
import info.neilqin.exceptions.ValidatorException;
import info.neilqin.repository.GoodsDao;
import info.neilqin.repository.GoodsRepository;
import info.neilqin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Neil
 * @date 2018/11/14 13:43
 */
@Controller
public class DemoController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    GoodsDao goodsRepository;
    @RequestMapping("/t1")
    public JSONView<String> hello(){
        List<GoodsPO> all = goodsRepository.getAll();
        GoodsPO byId = goodsRepository.getById("1");
        System.out.println(111);
        return JSONView.parseSuccess("112");
    }
    @RequestMapping("/t2")
    public JSONView<String> hello2(String name,String age){
        return JSONView.parseSuccess(name);
    }
    @RequestMapping("/t3")
    public JSONView<String> hello3(@JsonParam String name,@JsonParam String age){
        return JSONView.parseSuccess(name);
    }
    @RequestMapping("/t4")
    public JSONView<String> hello4(@JsonParam(value = "name",defaultValue = "abc") String aname,String age){
        return JSONView.parseSuccess(aname);
    }
    @RequestMapping("/t5")
    public JSONView<String> hello5(@JsonParam(defaultValue = "张三") String name,String age){
        return JSONView.parseSuccess(name);
    }
    @RequestMapping("/t6")
    public JSONView<UserPO> hello6(Long phone){
        return JSONView.parseSuccess(this.userRepository.findOne(phone));
    }

}
