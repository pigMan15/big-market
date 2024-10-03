package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivity
 * @date 2024/10/3
 * @description 抽奖活动表
 */
@Data
public class RaffleActivity {

    private Long id;

    // 活动
    private Long activityId;

    // 活动名称
    private String activityName;

    // 活动描述
    private String activityDesc;

    // 开始时间
    private Date beginDateTime;

    // 结束时间
    private Date endDateTime;

    // 库存总量
    private Integer stockCount;

    // 剩余库存
    private Integer stockCountSurplus;

    // 活动参与次数配置
    private Long activityCountId;

    // 抽奖策略id
    private Long strategyId;

    // 活动状态
    private String state;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
