package net.pigman.domain.strategy.service.rule.filter;

import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.entity.RuleMatterEntity;

/**
 * packageName net.pigman.domain.strategy.service
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IlogicFilter
 * @date 2024/9/5
 * @description 抽奖规则过滤接口
 */
public interface IlogicFilter<T extends RuleActionEntity.RaffleEntity> {

    /**
     * @description
     * @param ruleMatterEntity:
     * return RuleActionEntity<T>
     * @author pig泉
     * @date 22:03 2024/9/5
     * {@link RuleActionEntity<T>}
     */
    RuleActionEntity<T> filter(RuleMatterEntity ruleMatterEntity);

}
