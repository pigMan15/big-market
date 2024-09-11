package net.pigman.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RaffleAwardEntity;
import net.pigman.domain.strategy.model.entity.RaffleFactorEntity;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.model.entity.StrategyEntity;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import net.pigman.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.armory.IStrategyDispatch;
import net.pigman.domain.strategy.service.rule.chain.ILogicChain;
import net.pigman.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import net.pigman.domain.strategy.service.rule.filter.factory.DefaultLogicFactory;
import net.pigman.types.enums.ResponseCode;
import net.pigman.types.exception.AppException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * packageName net.pigman.domain.strategy.service.raffle
 *
 * @author pig泉
 * @version 1.0.0
 * @className AbstractRaffleStrategy
 * @date 2024/9/5
 * @description 抽奖策略抽象类, 定义抽奖标准流程
 */
@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {

    // 策略仓储服务
    protected IStrategyRepository repository;

    // 策略调度服务
    protected IStrategyDispatch dispatch;

    private DefaultChainFactory defaultChainFactory;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch dispatch, DefaultChainFactory defaultChainFactory) {
        this.repository = repository;
        this.dispatch = dispatch;
        this.defaultChainFactory = defaultChainFactory;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {

        // 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if (Objects.isNull(strategyId) || StringUtils.isEmpty(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 获取抽奖责任链，前置规则责任链处理
        ILogicChain iLogicChain = defaultChainFactory.openLogicChain(strategyId);

        // 获取最终校验结果：奖品id
        Integer awardId = iLogicChain.logic(userId, strategyId);

        // 查询奖品规则（抽奖中：根据奖品id查询是否还需进行次数锁过滤；抽奖后：扣减完奖品库存后过滤，抽奖中拦截和无库存则走兜底）
        StrategyAwardRuleModelVO awardRuleModelVO = repository.queryStrategyAwardRuleModelVO(strategyId, awardId);

        // 抽奖中-规则过滤
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> ruleActionCenterEntity = this.doCheckRaffCenterLogic(
                RaffleFactorEntity.builder().strategyId(strategyId).userId(userId).awardId(awardId).build(),
                awardRuleModelVO.raffleCenterRuleModelList()
        );

        if(RuleLogicCheckTypeVO.TAKE_OVER.getCode().equalsIgnoreCase(ruleActionCenterEntity.getCode())) {
           log.info("【抽奖中规则拦截】, 通过抽奖后规则rule_luck_award 走兜底奖品");
           return RaffleAwardEntity.builder().awardDesc("抽奖中规则拦截, 通过抽奖后规则rule_luck_award 走兜底奖品").build();
        }

        return RaffleAwardEntity.builder().awardId(awardId).build();
    }

    protected  abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffBeforeLogic(RaffleFactorEntity factorEntity, String... logics);

    protected  abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffCenterLogic(RaffleFactorEntity factorEntity, String... logics);

}
