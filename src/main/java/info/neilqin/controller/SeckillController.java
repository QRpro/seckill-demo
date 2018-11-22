package info.neilqin.controller;

import com.google.common.util.concurrent.RateLimiter;
import info.neilqin.anno.JsonParam;
import info.neilqin.api.ISeckillService;
import info.neilqin.common.constants.Constants;
import info.neilqin.common.enums.ResultEnum;
import info.neilqin.common.views.JSONView;
import info.neilqin.entity.po.UserPO;
import info.neilqin.exceptions.GlobalException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Neil
 * @date 2018/11/20 17:10
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private ISeckillService seckillService;

    private RateLimiter rateLimiter =  RateLimiter.create(2);

    @PostMapping("/path/{goodsId}")
    public JSONView<String> getSeckillPath(@PathVariable("goodsId")Long goodsId, UserPO user){
        String path = this.seckillService.getSeckillPath(goodsId,user);
        return JSONView.parseSuccess(path);
    }

    @PostMapping("/do_seckill")
    public JSONView<Integer> doSeckill(@JsonParam String token,@JsonParam Long goodsId, UserPO user){
        // 判断能否在1秒内得到令牌，如果不能则立即返回，不会阻塞程序
        if (!rateLimiter.tryAcquire(1, TimeUnit.SECONDS)){
            throw GlobalException.REQUEST_LIMIT;
        }
        this.seckillService.seckill(token,goodsId,user);
        return JSONView.parseSuccess(Constants.SeckillStatus.SECKILL_INQUEUE);
    }

    @GetMapping("/result")
    public JSONView<Long> seckillResult(Long goodsId,UserPO user){
        long result = this.seckillService.getSeckillResult(user.getId(), goodsId);
        return JSONView.parseSuccess(result);
    }
}
