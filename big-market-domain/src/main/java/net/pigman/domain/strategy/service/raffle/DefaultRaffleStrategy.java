package net.pigman.domain.strategy.service.raffle;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RaffleFactorEntity;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.entity.RuleMatterEntity;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.AbstractRaffleStrategy;
import net.pigman.domain.strategy.service.armory.IStrategyDispatch;
import net.pigman.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import net.pigman.domain.strategy.service.rule.filter.IlogicFilter;
import net.pigman.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * packageName net.pigman.domain.strategy.service.raffle
 *
 * @author pig泉
 * @version 1.0.0
 * @className DefaultRaffleStrategy
 * @date 2024/9/5
 * @description 抽奖策略
 */
@Slf4j
@Service
public class DefaultRaffleStrategy extends AbstractRaffleStrategy {

    @Resource
    private DefaultLogicFactory logicFactory;

    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch dispatch, DefaultChainFactory defaultChainFactory) {
        super(repository, dispatch, defaultChainFactory);
    }

    @Override
    protected RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffBeforeLogic(RaffleFactorEntity factorEntity, String... logics) {
        Map<String, IlogicFilter<RuleActionEntity.RaffleBeforeEntity>> ilogicFilterMap = logicFactory.openLogicFilter();

        // 黑名单规则优先过滤
        String ruleBlackList = Arrays.asList(logics).stream().filter(s -> s.contains(DefaultLogicFactory.LogicMode.RULE_BLACKLIST.getCode()))
                .findFirst().orElse(null);

        if (StringUtils.isNotBlank(ruleBlackList)) {
            IlogicFilter<RuleActionEntity.RaffleBeforeEntity> logicFilter = ilogicFilterMap.get(DefaultLogicFactory.LogicMode.RULE_BLACKLIST.getCode());
            RuleMatterEntity ruleMatterEntity = new RuleMatterEntity();
            ruleMatterEntity.setUserId(factorEntity.getUserId());
            ruleMatterEntity.setAwardId(ruleMatterEntity.getAwardId());
            ruleMatterEntity.setStrategyId(factorEntity.getStrategyId());
            ruleMatterEntity.setRuleModel(DefaultLogicFactory.LogicMode.RULE_BLACKLIST.getCode());
            RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = logicFilter.filter(ruleMatterEntity);
            if (!RuleLogicCheckTypeVO.ALLOW.getCode().equals(ruleActionEntity.getCode())) {
                log.info("抽奖前规则过滤, userId:{}, ruleModel:{}, code:{}, info:{}", factorEntity.getUserId(),
                        DefaultLogicFactory.LogicMode.RULE_BLACKLIST.getCode(), ruleActionEntity.getCode(), ruleActionEntity.getInfo());
                return ruleActionEntity;
            }
        }

        // 其他过滤规则
        List<String> ruleList = Arrays.asList(logics)
                .stream().filter(s -> !s.equals(DefaultLogicFactory.LogicMode.RULE_BLACKLIST.getCode()))
                .collect(Collectors.toList());
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> ruleActionEntity = null;
        for (String ruleModel: ruleList) {
            IlogicFilter<RuleActionEntity.RaffleBeforeEntity> logicFilter = ilogicFilterMap.get(ruleModel);
            RuleMatterEntity ruleMatterEntity = new RuleMatterEntity();
            ruleMatterEntity.setUserId(factorEntity.getUserId());
            ruleMatterEntity.setAwardId(factorEntity.getAwardId());
            ruleMatterEntity.setStrategyId(factorEntity.getStrategyId());
            ruleMatterEntity.setRuleModel(ruleModel);
            ruleActionEntity = logicFilter.filter(ruleMatterEntity);
            log.info("抽奖前规则过滤, userId:{}, ruleModel:{}, code:{}, info:{}", factorEntity.getUserId(), ruleModel, ruleActionEntity.getCode(), ruleActionEntity.getInfo());
            if(!RuleLogicCheckTypeVO.ALLOW.getCode().equals(ruleActionEntity.getCode())) {
                return ruleActionEntity;
            }
        }
        if (Objects.isNull(ruleActionEntity)) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        } else {
            return ruleActionEntity;
        }
    }

    @Override
    protected RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffCenterLogic(RaffleFactorEntity factorEntity, String... logics) {

        if(Objects.isNull(logics) || 0 == logics.length) {
            // 没有配置抽奖中拦截规则，直接返回
            return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        Map<String, IlogicFilter<RuleActionEntity.RaffleCenterEntity>> ilogicFilterMap = logicFactory.openLogicFilter();
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> ruleActionEntity = null;
        for (String ruleModel: logics) {
            IlogicFilter<RuleActionEntity.RaffleCenterEntity> logicFilter = ilogicFilterMap.get(ruleModel);
            RuleMatterEntity ruleMatterEntity = new RuleMatterEntity();
            ruleMatterEntity.setUserId(factorEntity.getUserId());
            ruleMatterEntity.setAwardId(factorEntity.getAwardId());
            ruleMatterEntity.setStrategyId(factorEntity.getStrategyId());
            ruleMatterEntity.setRuleModel(ruleModel);
            ruleActionEntity = logicFilter.filter(ruleMatterEntity);
            log.info("抽奖中规则过滤, userId:{}, ruleModel:{}, code:{}, info:{}", factorEntity.getUserId(), ruleModel, ruleActionEntity.getCode(), ruleActionEntity.getInfo());
            if(!RuleLogicCheckTypeVO.ALLOW.getCode().equals(ruleActionEntity.getCode())) {
                return ruleActionEntity;
            }
        }
        if (Objects.isNull(ruleActionEntity)) {
            return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        } else {
            return ruleActionEntity;
        }
    }
}
