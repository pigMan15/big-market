package net.pigman.domain.activity.event;

import net.pigman.types.event.BaseEvent;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * packageName net.pigman.domain.activity.event
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivitySkuStockZeroMessageEvent
 * @date 2024/10/16
 * @description 活动sku库存清空消息
 */
@Component
public class ActivitySkuStockZeroMessageEvent extends BaseEvent<Long> {

    @Value("${spring.rabbitmq.topic.activity_sku_stock_zero}")
    private String topic;

    @Override
    public EventMessage<Long> buildMessage(Long sku) {
        return EventMessage.<Long>builder().id(RandomStringUtils.randomNumeric(11))
                .tiemstamp(new Date()).data(sku)
                .build();
    }

    @Override
    public String topic() {
        return topic;
    }
}
