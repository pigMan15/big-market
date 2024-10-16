package net.pigman.infrastructure.event;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.types.event.BaseEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * packageName net.pigman.infrastructure.event
 *
 * @author pig泉
 * @version 1.0.0
 * @className RedisPublisher
 * @date 2024/10/16
 * @description 消息生产者
 */
@Slf4j
@Component
public class EventPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publish(String topic, BaseEvent.EventMessage<?> eventMessage) {
        try {
            String msg = JSON.toJSONString(eventMessage);
            rabbitTemplate.convertAndSend(topic, msg);
            log.info("发送MQ消息 topic:{}, message:{}", topic, msg);
        } catch (Exception e) {
            log.info("发送MQ消息失败 topic:{}, message:{}, error:{}", topic, JSON.toJSONString(eventMessage), e);
        }
    }

}
