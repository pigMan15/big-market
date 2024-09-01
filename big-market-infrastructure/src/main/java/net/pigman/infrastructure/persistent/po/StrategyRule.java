package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyRule
 * @date 2024/9/1
 * @description 抽奖策略规则
 */
@Data
public class StrategyRule {

    private Long id;

    private Long strategyId;

    private Long awardId;

    private Integer ruleType;

    private String ruleModel;

    private String ruleValue;

    private String ruleDesc;

    private Date createTime;

    private Date updateTime;

}
