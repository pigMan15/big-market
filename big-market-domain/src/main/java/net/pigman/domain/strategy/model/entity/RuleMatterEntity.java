package net.pigman.domain.strategy.model.entity;

import lombok.Data;

/**
 * packageName net.pigman.domain.strategy.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleMatterEntity
 * @date 2024/9/5
 * @description 规则物料实体对象
 */
@Data
public class RuleMatterEntity {

    // 用户id
    private String userId;

    // 策略id
    private Long strategyId;

    // 抽奖奖品id
    private Integer awardId;

    // 抽奖规则类型(rule_random:随机值计算, rule_lock:抽奖几次后解锁, rule_lock_award:幸运奖(兜底奖品))
    private String ruleModel;

}
