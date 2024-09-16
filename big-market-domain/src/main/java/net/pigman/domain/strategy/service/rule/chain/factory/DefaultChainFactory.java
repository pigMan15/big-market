package net.pigman.domain.strategy.service.rule.chain.factory;

import lombok.*;
import net.pigman.domain.strategy.model.entity.StrategyEntity;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.domain.strategy.service.rule.chain.ILogicChain;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * packageName net.pigman.domain.strategy.service.rule.chain.factory
 *
 * @author pig泉
 * @version 1.0.0
 * @className DefaultChainFactory
 * @date 2024/9/11
 * @description 责任链工厂
 */
@Service
public class DefaultChainFactory {

    private final Map<String, ILogicChain> logicChainGroup;

    private IStrategyRepository repository;

    public DefaultChainFactory(Map<String, ILogicChain> logicChainGroup, IStrategyRepository repository) {
        this.logicChainGroup = logicChainGroup;
        this.repository = repository;
    }

    /**
     * @description 构建责任链
     * @param strategyId:
     * return ILogicChain
     * @author pig泉
     * @date  2024/9/11
     * {@link ILogicChain}
     */
    public ILogicChain openLogicChain(Long strategyId) {

        StrategyEntity strategyEntity = repository.queryStrategyEntityByStrategyId(strategyId);
        String[] ruleModels = strategyEntity.ruleModels();

        if (Objects.isNull(ruleModels) || ruleModels.length == 0) {
            // 不存在策略规则，用默认责任链进行处理
            return logicChainGroup.get("default");
        }

        ILogicChain iLogicChain = logicChainGroup.get(ruleModels[0]);
        ILogicChain current = iLogicChain;
        for (int i=1; i<ruleModels.length; i++) {
            current = current.appendNext(logicChainGroup.get(ruleModels[i]));
        }
        current.appendNext(logicChainGroup.get("default"));
        return iLogicChain;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {

        private Integer awardId;

        private String logicModel;

    }

    @Getter
    @AllArgsConstructor
    public enum LogicModel {
        RULE_WEIGHT("rule_weight", "权重抽奖"),
        RULE_BLACKLIST("rule_blacklist", "黑名单抽奖"),
        RULE_DEFAULT("rule_default", "默认抽奖");

        private final String code;
        private final String info;
    }

}
