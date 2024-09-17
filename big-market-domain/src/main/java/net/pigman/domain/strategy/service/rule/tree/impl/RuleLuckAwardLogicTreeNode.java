package net.pigman.domain.strategy.service.rule.tree.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.service.rule.tree.ILogicTreeNode;
import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import net.pigman.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * packageName net.pigman.domain.strategy.service.rule.tree.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleLuckAwardLogicTreeNode
 * @date 2024/9/14
 * @description 兜底奖励节点
 */
@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-兜底奖品 userId:{}, strategyId:{}, awardId:{}, ruleValue:{}", userId, strategyId, awardId, ruleValue);

        String[] split = ruleValue.split(Constants.COLON);
        if (split.length == 0) {
            log.error("规则过滤-兜底奖品，兜底奖品未配置 userId:{}, strategyId:{}, awardId:{}, ruleValue:{}", userId, strategyId, awardId, ruleValue);
            throw new RuntimeException("兜底奖品未配置 " + ruleValue);
        }

        Integer luckAwardId = Integer.valueOf(split[0]);
        String awardRuleValue = split.length > 1 ? split[1] : StringUtils.EMPTY;

        // 兜底奖励接管
        log.info("规则过滤-兜底奖品 userId:{}, strategyId:{}, awardId:{}, awardRuleValue:{}", userId, strategyId, luckAwardId, awardRuleValue);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                        .awardId(luckAwardId).awardRuleValue(awardRuleValue).build())
                .build();
    }

}
