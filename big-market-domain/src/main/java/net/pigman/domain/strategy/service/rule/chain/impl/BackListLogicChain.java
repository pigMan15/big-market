package net.pigman.domain.strategy.service.rule.chain.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.rule.chain.AbstractLogicChain;
import net.pigman.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import net.pigman.types.common.Constants;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Arrays;

/**
 * packageName net.pigman.domain.strategy.service.rule.chain.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className BackListLogicChain
 * @date 2024/9/11
 * @description 黑名单责任链
 */
@Slf4j
@Component("rule_blacklist")
public class BackListLogicChain extends AbstractLogicChain {

    @Resource
    private IStrategyRepository repository;

    @Override
    protected String ruleModel() {
        return DefaultChainFactory.LogicModel.RULE_BLACKLIST.getCode();
    }

    @Override
    public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
        log.info("抽奖责任链-黑名单开始, userId:{}, strategyId:{}, ruleModel:{}", userId, strategyId, ruleModel());

        String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
        String[] ruleValues = ruleValue.split(Constants.COLON);
        Integer awardId = Integer.valueOf(ruleValues[0]);

        String[] blackUserIds = ruleValues[1].split(Constants.SPLIT);
        if (Arrays.asList(blackUserIds).contains(userId)) {
            log.info("抽奖责任链-黑名单接管, userId:{}, strategyId:{}, ruleModel:{}, awardId:{}", userId, strategyId, ruleModel(), awardId);
            return DefaultChainFactory.StrategyAwardVO.builder().awardId(awardId).logicModel(ruleModel()).build();
        }
        log.info("抽奖责任链-黑名单放行 userId:{}, strategyId:{}, ruleModel:{}", userId, strategyId, ruleModel());
        return next().logic(userId, strategyId);
    }
}
