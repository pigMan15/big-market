package net.pigman.domain.strategy.service.raffle;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.StrategyAwardEntity;
import net.pigman.domain.strategy.model.valobj.RuleTreeVO;
import net.pigman.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import net.pigman.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.AbstractRaffleStrategy;
import net.pigman.domain.strategy.service.IRaffleAward;
import net.pigman.domain.strategy.service.IRaffleStock;
import net.pigman.domain.strategy.service.armory.IStrategyDispatch;
import net.pigman.domain.strategy.service.rule.chain.ILogicChain;
import net.pigman.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import net.pigman.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import net.pigman.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
public class DefaultRaffleStrategy extends AbstractRaffleStrategy implements IRaffleStock, IRaffleAward {

    @Autowired
    private DefaultChainFactory defaultChainFactory;
    @Autowired
    private DefaultTreeFactory defaultTreeFactory;

    public DefaultRaffleStrategy(IStrategyRepository repository, IStrategyDispatch dispatch, DefaultChainFactory defaultChainFactory, DefaultTreeFactory defaultTreeFactory, DefaultLogicFactory logicFactory) {
        super(repository, dispatch, defaultChainFactory, defaultTreeFactory);
    }

    @Override
    public DefaultChainFactory.StrategyAwardVO raffleLogicChain(String userId, Long strategyId) {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        return logicChain.logic(userId, strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO raffleLogicTree(String userId, Long strategyId, Integer awardId) {
        StrategyAwardRuleModelVO awardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);
        if (Objects.isNull(awardRuleModelVO)) {
            return DefaultTreeFactory.StrategyAwardVO.builder().awardId(awardId).build();
        }
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId(awardRuleModelVO.getRuleModels());
        if (Objects.isNull(ruleTreeVO)) {
            throw new RuntimeException("存在抽奖策略配置的规则模型key, 未在库表rule_tree,rule_tree_node,rule_tree_node_line配置对应的决策树信息 " + awardRuleModelVO.getRuleModels());
        }
        IDecisionTreeEngine decisionTreeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        return decisionTreeEngine.process(userId, strategyId, awardId);
    }


    @Override
    public StrategyAwardStockKeyVO takeQueueValue() {
        return repository.takeQueueValue();
    }

    @Override
    public void updateStrategyAwardStock(Long strategyId, Integer awardId) {
        repository.updateStrategyAwardStock(strategyId, awardId);
    }

    @Override
    public List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId) {
        return repository.queryStrategyAwardList(strategyId);
    }
}
