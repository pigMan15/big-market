package net.pigman.domain.strategy.service.rule.filter.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.entity.RuleMatterEntity;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.annotation.LogicStrategy;
import net.pigman.domain.strategy.service.rule.filter.IlogicFilter;
import net.pigman.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import net.pigman.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * packageName net.pigman.domain.strategy.service.rule.filter.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleWeightLogicFilter
 * @date 2024/9/5
 * @description [抽奖前规则]根据抽奖权重返回可抽奖范围
 */
@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicMode.RULE_WEIGHT)
public class RuleWeightLogicFilter implements IlogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepository strategyRepository;

    public Long userScore = 4500L;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(RuleMatterEntity ruleMatterEntity) {

        log.info("规则过滤-权重范围 userId:{} strategyId:{} ruleModel:{}", ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

        String userId = ruleMatterEntity.getUserId();
        Long strategyId = ruleMatterEntity.getStrategyId();
        String ruleValue = strategyRepository.queryStrategyRuleValue(strategyId, ruleMatterEntity.getAwardId(), ruleMatterEntity.getRuleModel());

        // 获取权重值
        Map<Long, String> valueGroup = getWeightMap(ruleValue);
        if (Objects.isNull(valueGroup) || CollectionUtils.isEmpty(valueGroup)) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 排序
        List<Long> sortedKeys = new ArrayList<>(valueGroup.keySet());
        Collections.sort(sortedKeys);

        // 匹配当前用户积分对应的奖品范围
        Long value = sortedKeys.stream().filter(o -> userScore >= o).findFirst().orElse(null);

        if (Objects.nonNull(value)) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .strategyId(strategyId).ruleWeightValueKey(valueGroup.get(value))
                            .build())
                    .ruleModel(DefaultLogicFactory.LogicMode.RULE_WEIGHT.getCode())
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }

    public Map<Long, String> getWeightMap(String ruleValue) {
        Map<Long, String> ruleValueMap = new HashMap<>(6);
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        for (String ruleValueKey: ruleValueGroups) {
            if (StringUtils.isEmpty(ruleValueKey)) {
                return ruleValueMap;
            }
            String[] values = ruleValueKey.split(Constants.COLON);
            if (values.length != 2) {
                throw new IllegalArgumentException("rule_weight rule invalid format: " + ruleValueKey);
            }
            ruleValueMap.put(Long.parseLong(values[0]), ruleValueKey);
        }
        return ruleValueMap;
    }
}
