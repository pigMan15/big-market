package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivityAccount
 * @date 2024/10/3
 * @description 抽奖活动账户表
 */
@Data
public class RaffleActivityAccount {

    private Long id;

    // 用户id
    private String userId;

    // 活动id
    private Long activityId;

    // 总次数
    private Integer totalCount;

    // 总次数-剩余
    private Integer totalCountSurplus;

    // 日次数
    private Integer dayCount;

    // 日次数-剩余
    private Integer dayCountSurplus;

    // 月次数
    private Integer monthCount;

    // 月次数-剩余
    private Integer monthCountSurplus;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
