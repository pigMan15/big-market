package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivitySku
 * @date 2024/10/7
 * @description 抽奖活动sku持久化对象
 */
@Data
public class RaffleActivitySku {

    private Long sku;

    private Long activityId;

    private Long activityCountId;

    private Integer stockCount;

    private Integer stockCountSurplus;

    private Date createTime;

    private Date updateTime;

}
