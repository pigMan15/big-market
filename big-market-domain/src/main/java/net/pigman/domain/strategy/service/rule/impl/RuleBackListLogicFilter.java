package net.pigman.domain.strategy.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.entity.RuleMatterEntity;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.annotation.LogicStrategy;
import net.pigman.domain.strategy.service.rule.IlogicFilter;
import net.pigman.domain.strategy.service.rule.factory.DefaultLogicFactory;
import net.pigman.types.common.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * packageName net.pigman.domain.strategy.service.rule.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleBackListLogicFilter
 * @date 2024/9/5
 * @description 黑名单规则过滤
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicMode.RULE_BLACKLIST)
public class RuleBackListLogicFilter implements IlogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-黑名单 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

        String userId = ruleMatterEntity.getUserId();
        String ruleValue = strategyRepository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(), ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());
        String[] ruleValues = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.parseInt(ruleValues[0]);

        String[] blackUserIds = ruleValues[1].split(Constants.SPLIT);
        if (Arrays.asList(blackUserIds).contains(userId)) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .ruleModel(DefaultLogicFactory.LogicMode.RULE_BLACKLIST.getCode())
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .strategyId(ruleMatterEntity.getStrategyId())
                            .awardId(awardId)
                            .build())
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }

        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();

    }
}
