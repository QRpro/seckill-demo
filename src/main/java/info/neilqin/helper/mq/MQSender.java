package info.neilqin.helper.mq;

import info.neilqin.entity.po.OrderPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Neil
 * @date Create in 0:40 2018/11/21
 */
@Component
@Slf4j
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void  sendSeckillMsg(SeckillMsg msg){
        log.info("send seckill msg : {}",msg);
        this.amqpTemplate.convertAndSend(msg);
    }
}
