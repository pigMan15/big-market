package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivityOrder
 * @date 2024/10/3
 * @description 抽奖活动订单
 */
@Data
public class RaffleActivityOrder {

    private Long id;

    // 用户id
    private String userId;

    // sku
    private Long sku;

    // 活动id
    private Long activityId;

    // 活动名称
    private String activityName;

    // 抽奖策略id
    private Long strategyId;

    // 订单id
    private String orderId;

    // 下单时间
    private Date orderTime;

    // 总次数
    private Integer totalCount;

    // 日次数
    private Integer dayCount;

    // 月次数
    private Integer monthCount;

    // 订单状态(not_used, used, expire)
    private String state;

    // 业务外部id
    private String outBusinessNo;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
