package net.pigman.types.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * packageName net.pigman.types.event
 *
 * @author pig泉
 * @version 1.0.0
 * @className BaseEvent
 * @date 2024/10/16
 * @description 消息基类
 */
@Data
public abstract class BaseEvent<T> {

    /**
     * @description 构造mq消息
     * @param data:
     * return EventMessage<T>
     * @author pig泉
     * @date 21:15 2024/10/16
     * {@link EventMessage<T>}
     */
    public abstract EventMessage<T> buildMessage(T data);

    /**
     * @description 消息对应topic
     * @param :
     * return String
     * @author pig泉
     * @date 21:15 2024/10/16
     * {@link String}
     */
    public abstract String topic();


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventMessage<T> {

        private String id;

        private Date tiemstamp;

        private T data;

    }

}
