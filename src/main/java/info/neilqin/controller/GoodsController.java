package info.neilqin.controller;

import info.neilqin.api.IGoodsService;
import info.neilqin.entity.vo.GoodsDetailVO;
import info.neilqin.entity.vo.GoodsVO;
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
        List<GoodsVO> dto = this.goodsService.findAll();
        model.addAttribute("goodsList", dto);
        return "/goods/list";
    }
    @RequestMapping("/detail")
    public String goodsDetail(Long goodsId,Model model){
        GoodsVO goods = this.goodsService.getGoodsSeckillDetail(goodsId);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;

        if (now < startTime) {//秒杀还没开始，倒计时
            seckillStatus = 0;
            remainSeconds = (int) ((startTime - now) / 1000);
        } else if (now > endTime) {//秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVO vo = new GoodsDetailVO();
        vo.setGoods(goods);
//        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setSeckillStatus(seckillStatus);
        model.addAttribute("goodsDetail", vo);
        return "/goods/detail";
    }
}
