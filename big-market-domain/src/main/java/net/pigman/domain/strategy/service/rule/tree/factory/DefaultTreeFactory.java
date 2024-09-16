package net.pigman.domain.strategy.service.rule.tree.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.model.valobj.RuleTreeVO;
import net.pigman.domain.strategy.service.rule.tree.ILogicTreeNode;
import net.pigman.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import net.pigman.domain.strategy.service.rule.tree.factory.engine.impl.DecisionTreeEngine;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * packageName net.pigman.domain.strategy.service.rule.tree.factory
 *
 * @author pig泉
 * @version 1.0.0
 * @className DefaultTreeFactory
 * @date 2024/9/14
 * @description 规则树工厂
 */
@Service
public class DefaultTreeFactory {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    public DefaultTreeFactory(Map<String, ILogicTreeNode> logicTreeNodeGroup) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
    }

    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO) {
        return new DecisionTreeEngine(logicTreeNodeGroup, ruleTreeVO);
    }

    /**
     * @description 决策树对应的执行动作
     * @author pig泉
     * @date 15:03 2024/9/14
     * {@link null}
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeActionEntity {

        private RuleLogicCheckTypeVO ruleLogicCheckType;

        private StrategyAwardVO strategyAwardVO;

    }

    /**
     * @description 抽奖策略奖品
     * @author pig泉
     * @date 15:04 2024/9/14
     * {@link null}
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {

        // 抽奖奖品id
        private Integer awardId;
        // 抽奖奖品规则
        private String awardRuleValue;

    }

}
