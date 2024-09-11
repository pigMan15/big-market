package net.pigman.domain.strategy.service.rule.chain.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.armory.IStrategyDispatch;
import net.pigman.domain.strategy.service.rule.chain.AbstractLogicChain;
import net.pigman.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * packageName net.pigman.domain.strategy.service.rule.chain.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleWeightLogicChain
 * @date 2024/9/11
 * @description 权重抽奖责任链
 */
@Slf4j
@Component("rule_weight")
public class RuleWeightLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Resource
    private IStrategyDispatch strategyDispatch;

    public Long userScore = 0L;

    @Override
    protected String ruleModel() {
        return "rule_weight";
    }

    @Override
    public Integer logic(String userId, Long strategyId) {

        log.info("抽奖责任链-权重开始, userId:{}, strategyId:{}, ruleModel:{}", userId, strategyId, ruleModel());
        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());

        // 获取权重值
        Map<Long, String> valueGroup = getWeightMap(ruleValue);
        if (Objects.isNull(valueGroup) || CollectionUtils.isEmpty(valueGroup)) {
            return null;
        }

        // 排序
        List<Long> sortedKeys = new ArrayList<>(valueGroup.keySet());
        Collections.sort(sortedKeys);

        // 匹配当前用户积分对应的奖品范围
        Long value = sortedKeys.stream().filter(o -> userScore >= o).findFirst().orElse(null);

        if (Objects.nonNull(value)) {
            Integer awardId = strategyDispatch.getRandomAwardId(strategyId, valueGroup.get(value));
            log.info("抽奖责任链-权重接管, userId:{}, strategyId:{}, ruleModel:{}, awardId:{}", userId, strategyId, ruleModel(), awardId);
            return awardId;
        }

        log.info("抽奖责任链-权重放行 userId:{}, strategyId:{}, ruleModel:{}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
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
