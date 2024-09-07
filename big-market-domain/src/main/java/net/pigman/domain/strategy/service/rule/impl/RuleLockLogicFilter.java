package net.pigman.domain.strategy.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.entity.RuleMatterEntity;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.annotation.LogicStrategy;
import net.pigman.domain.strategy.service.rule.IlogicFilter;
import net.pigman.domain.strategy.service.rule.factory.DefaultLogicFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * packageName net.pigman.domain.strategy.service.rule.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleLockLogicFilter
 * @date 2024/9/7
 * @description 用户抽奖n次后，解锁对应抽奖奖品
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicMode.RULE_LOCK)
public class RuleLockLogicFilter implements IlogicFilter<RuleActionEntity.RaffleCenterEntity> {

    @Resource
    private IStrategyRepository repository;

    // 用户抽奖次数, 目前写死，后续需要从数据库或redis缓存中获取
    private Long userRaffleCount = 0l;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleCenterEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-次数解锁，userId:{}, strategyId:{}, ruleModel:{}",
                ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

        // 查询配置的抽奖次数规则
        String ruleValue = repository.queryStrategyRuleValue(
                ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());

        // 判断是否过滤
        if (userRaffleCount >= Long.parseLong(ruleValue)) {
            return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 未达到对应解锁次数
        return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                .build();

    }

}
