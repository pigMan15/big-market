package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivityCount
 * @date 2024/10/3
 * @description 抽奖活动次数配置表
 */
@Data
public class RaffleActivityCount {

    private Long id;

    // 活动次数配置
    private Long activityCountId;

    // 总次数
    private Integer totalCount;

    // 日次数
    private Integer dayCount;

    // 月次数
    private Integer monthCount;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
