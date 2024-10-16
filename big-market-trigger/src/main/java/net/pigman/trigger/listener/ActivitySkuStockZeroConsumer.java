package net.pigman.trigger.listener;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.service.ISkuStock;
import net.pigman.types.event.BaseEvent;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * packageName net.pigman.trigger.listener
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivitySkuStockZeroConsumer
 * @date 2024/10/16
 * @description 活动sku库存消费者
 */
@Slf4j
@Component
public class ActivitySkuStockZeroConsumer {

    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;

    @Resource
    private ISkuStock skuStock;

    @RabbitListener(queuesToDeclare = @Queue(value = "activity_sku_stock_zero"))
    public void listener(String message) {
        try {
            log.info("消费活动sku库存扣减为0消息，topic:{}, message:{}", topic, message);
            BaseEvent.EventMessage<Long> eventMessage = JSON.parseObject(message,
                    new TypeReference<BaseEvent.EventMessage<Long>>() {
                    }.getType());
            skuStock.clearActivitySkuStock(eventMessage.getData());
            skuStock.clearQueueValue();
        } catch (Exception e) {
            log.info("消费活动sku库存扣减为0消息失败，topic:{}, message:{}, error:{}", topic, message, e);
            throw e;
        }

    }

}
