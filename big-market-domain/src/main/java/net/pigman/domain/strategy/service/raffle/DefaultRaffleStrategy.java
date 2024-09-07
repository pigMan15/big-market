package net.pigman.domain.strategy.service.raffle;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RaffleFactorEntity;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.entity.RuleMatterEntity;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.armory.IStrategyDispatch;
import net.pigman.domain.strategy.service.rule.IlogicFilter;
import net.pigman.domain.strategy.service.rule.factory.DefaultLogicFactory;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.rule.Rule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
public class DefaultRaffleStrategy extends AbstractRaffleStrategy{

    @Resource
    private DefaultLogicFactory logicFactory;

    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch dispatch) {
        super(repository, dispatch);
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
            ruleMatterEntity.setAwardId(ruleMatterEntity.getAwardId());
            ruleMatterEntity.setStrategyId(factorEntity.getStrategyId());
            ruleMatterEntity.setRuleModel(ruleModel);
            ruleActionEntity = logicFilter.filter(ruleMatterEntity);
            log.info("抽奖前规则过滤, userId:{}, ruleModel:{}, code:{}, info:{}", factorEntity.getUserId(), ruleModel, ruleActionEntity.getCode(), ruleActionEntity.getInfo());
            if(!RuleLogicCheckTypeVO.ALLOW.getCode().equals(ruleActionEntity.getCode())) {
                return ruleActionEntity;
            }
        }
        return ruleActionEntity;
    }
}
