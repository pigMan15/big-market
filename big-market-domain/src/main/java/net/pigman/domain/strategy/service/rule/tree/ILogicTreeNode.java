package net.pigman.domain.strategy.service.rule.tree;

import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * packageName net.pigman.domain.strategy.service.rule.tree
 *
 * @author pig泉
 * @version 1.0.0
 * @interface ILogicTreeNode
 * @date 2024/9/14
 * @description 规则树接口
 */
public interface ILogicTreeNode {

    DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue);

}
