package info.neilqin.controller;

import info.neilqin.api.IGoodsService;
import info.neilqin.entity.po.UserPO;
import info.neilqin.entity.vo.GoodsDetailVO;
import info.neilqin.entity.vo.GoodsVO;
import info.neilqin.interceptors.CheckLogin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品相关Controller
 * @author Neil
 * @date 2018/11/15 16:51
 */
@Controller
@RequestMapping("/goods")
@CheckLogin
public class GoodsController {

    @Autowired
    IGoodsService goodsService;

    @GetMapping("/list")
    public String goodsList(Model model, UserPO user){
        List<GoodsVO> dto = this.goodsService.findAll();
        model.addAttribute("goodsList", dto);
        return "/goods/list";
    }
    @GetMapping("/detail")
    public String goodsDetail(Long goodsId,Model model){
        GoodsVO goods = this.goodsService.getGoodsSeckillDetail(goodsId);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus;
        int remainSeconds;
        //秒杀还没开始，倒计时
        if (now < startTime) {
            seckillStatus = 0;
            remainSeconds = (int) ((startTime - now) / 1000);
        //秒杀已经结束
        } else if (now > endTime) {
            seckillStatus = 2;
            remainSeconds = -1;
        //秒杀进行中
        } else {
            seckillStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVO vo = new GoodsDetailVO();
        vo.setGoods(goods);
        vo.setRemainSeconds(remainSeconds);
        vo.setSeckillStatus(seckillStatus);
        model.addAttribute("goodsDetail", vo);
        return "/goods/detail";
    }
}
