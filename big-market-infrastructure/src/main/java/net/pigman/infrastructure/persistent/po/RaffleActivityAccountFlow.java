package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivityAccountFlow
 * @date 2024/10/3
 * @description 抽奖活动账户流水表
 */
@Data
public class RaffleActivityAccountFlow {

    private Integer id;

    // 用户id
    private String userId;

    // 活动id
    private Long activityId;

    // 总次数
    private Integer totalCount;

    // 日次数
    private Integer dayCount;

    // 月次数
    private Integer monthCount;

    // 流水id
    private String flowId;

    // 流水渠道(activity-活动领取，sale-购买，redeem-兑换，free-赠送)
    private String flowChannel;

    // 业务id(eg: 活动id, 订单id)
    private String bizId;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
