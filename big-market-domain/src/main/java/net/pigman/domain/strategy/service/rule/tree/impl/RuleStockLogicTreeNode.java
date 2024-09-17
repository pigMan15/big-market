package net.pigman.domain.strategy.service.rule.tree.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.armory.StrategyArmoryDispatch;
import net.pigman.domain.strategy.service.rule.tree.ILogicTreeNode;
import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * packageName net.pigman.domain.strategy.service.rule.tree.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleStockLogicTreeNode
 * @date 2024/9/14
 * @description 库存扣减节点
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {

    @Resource
    private StrategyArmoryDispatch strategyArmoryDispatch;

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {

        log.info("规则过滤-库存扣减, userId:{}, strategyId:{}, awardId:{}", userId, strategyId, awardId);

        // 扣减库存
        Boolean status = strategyArmoryDispatch.subtractionAwardStock(strategyId, awardId);
        if (status) {
            log.info("规则过滤-库存扣减-成功 userId:{}, strategyId:{}, awardId:{}", userId, strategyId, awardId);
            // 写入延迟队列，延迟消费更新数据库记录
            strategyRepository.awardStockConsumeSendQueue(
                    StrategyAwardStockKeyVO.builder().strategyId(strategyId).awardId(awardId).build()
            );
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                    .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                            .awardId(awardId)
                            .awardRuleValue(ruleValue)
                            .build())
                    .build();
        }

        // 库存扣减失败，直接放行
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }

}
