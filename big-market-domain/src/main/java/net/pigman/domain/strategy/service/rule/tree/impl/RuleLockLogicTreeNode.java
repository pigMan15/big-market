package net.pigman.domain.strategy.service.rule.tree.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.service.rule.tree.ILogicTreeNode;
import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import org.springframework.stereotype.Component;

/**
 * packageName net.pigman.domain.strategy.service.rule.tree.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleLockLogicTreeNode
 * @date 2024/9/14
 * @description 次数锁节点
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {

    private Long userRaffleCount = 10L;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-次数锁 userId:{}, strategyId:{}, awardId;{}", userId, strategyId, awardId);

        long raffleCount = 0L;
        try {
            raffleCount = Long.parseLong(ruleValue);
        } catch (Exception e) {
            throw new RuntimeException("规则过滤-次数锁异常, ruleValue: " + ruleValue + " 配置不正确");
        }
        if (userRaffleCount >= raffleCount) {
            // 达到奖品解锁次数，直接放行
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                    .build();
        }

        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();

    }

}
