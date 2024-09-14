package net.pigman.domain.strategy.service.rule.tree.factory.engine.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.model.valobj.RuleTreeNodeLineVO;
import net.pigman.domain.strategy.model.valobj.RuleTreeNodeVO;
import net.pigman.domain.strategy.model.valobj.RuleTreeVO;
import net.pigman.domain.strategy.service.rule.tree.ILogicTreeNode;
import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import net.pigman.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * packageName net.pigman.domain.strategy.service.rule.tree.factory.engine.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className DecisionTreeEngine
 * @date 2024/9/14
 * @description 决策树引擎
 */
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    private final RuleTreeVO ruleTreeVO;

    public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
        this.ruleTreeVO = ruleTreeVO;
    }

    @Override
    public DefaultTreeFactory.StrategyAwardData process(String userId, Long strategyId, Integer awardId) {
        DefaultTreeFactory.StrategyAwardData strategyAwardData = null;

        String nextNodeKey = ruleTreeVO.getTreeRootRuleNode();
        Map<String, RuleTreeNodeVO> treeNodeMap = ruleTreeVO.getTreeNodeMap();

        // 起始节点
        RuleTreeNodeVO rootNode = treeNodeMap.get(nextNodeKey);
        while (null != rootNode) {
            // 决策节点
            ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(rootNode.getRuleKey());

            // 决策点计算
            DefaultTreeFactory.TreeActionEntity treeActionEntity = logicTreeNode.logic(userId, strategyId, awardId);
            RuleLogicCheckTypeVO ruleLogicCheckType = treeActionEntity.getRuleLogicCheckType();
            strategyAwardData = treeActionEntity.getStrategyAwardData();
            log.info("决策树引擎【{}】, treeId:{}, node:{}, code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(),
                    nextNodeKey, ruleLogicCheckType.getCode());

            // 获取下一节点
            nextNodeKey = nextNode(ruleLogicCheckType.getCode(), rootNode.getTreeNodeLineVOList());
            rootNode = treeNodeMap.get(nextNodeKey);
        }

        // 返回奖品
        return strategyAwardData;
    }


    public String nextNode(String matterValue, List<RuleTreeNodeLineVO> treeNodeLineVOList) {
        if (Objects.isNull(treeNodeLineVOList) || CollectionUtils.isEmpty(treeNodeLineVOList)) {
            return null;
        }
        for (RuleTreeNodeLineVO treeNodeLine: treeNodeLineVOList) {
            if (decisionLogic(matterValue, treeNodeLine)) {
                return treeNodeLine.getRuleNodeTo();
            }
        }
        throw new RuntimeException("决策树引擎异常，找不到可执行的下一个节点!");
    }

    /**
     * @description 匹配规则限定相同的连线数据
     * @param matterValue:
     * @param treeNodeLineVO:
     * return Boolean
     * @author pig泉
     * @date 16:10 2024/9/14
     * {@link Boolean}
     */
    public Boolean decisionLogic(String matterValue, RuleTreeNodeLineVO treeNodeLineVO) {
        switch(treeNodeLineVO.getRuleLimitType()) {
            case EQUAL:
                 return matterValue.equals(treeNodeLineVO.getRuleLimitValue().getCode());
            case GT:
                 break;
            case LT:
                 break;
            case GE:
                 break;
            case LE:
                 break;
            case ENUM:
                 break;
            default:
                return false;
        }
        return false;
    }



}
