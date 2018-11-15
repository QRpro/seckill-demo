package info.neilqin.controller;

import info.neilqin.api.IGoodsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Neil
 * @date 2018/11/15 16:51
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    IGoodsService goodsService;

    @RequestMapping("/list")
    public String goodsList(Model model){
        return "/goods/list";
    }
    @RequestMapping("/detail")
    public String goodsDetail(){
        return "/goods/detail";
    }
}
