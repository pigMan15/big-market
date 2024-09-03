package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className Strategy
 * @date 2024/9/1
 * @description 抽奖策略
 */
@Data
public class Strategy {

    private Long id;

    private Long strategyId;

    private String strategyDesc;

    private String ruleModels;

    private Date createTime;

    private Date updateTime;

}
