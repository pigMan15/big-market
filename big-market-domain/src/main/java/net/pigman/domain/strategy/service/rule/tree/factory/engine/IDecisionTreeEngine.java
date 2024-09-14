package net.pigman.domain.strategy.service.rule.tree.factory.engine;

import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * packageName net.pigman.domain.strategy.service.rule.tree.factory.engine
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IDecisionTreeEngine
 * @date 2024/9/14
 * @description 规则树组合接口
 */
public interface IDecisionTreeEngine {

    DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId);

}
