package net.pigman.domain.strategy.service.rule.factory;

import com.alibaba.fastjson2.util.AnnotationUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.pigman.domain.strategy.model.entity.RuleActionEntity;
import net.pigman.domain.strategy.service.annotation.LogicStrategy;
import net.pigman.domain.strategy.service.rule.IlogicFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName net.pigman.domain.strategy.service.rule.factory
 *
 * @author pig泉
 * @version 1.0.0
 * @className DefaultLogicFactory
 * @date 2024/9/5
 * @description 规则工厂
 */
@Service
public class DefaultLogicFactory {

    public Map<String, IlogicFilter<?>> logicFilterMap = new ConcurrentHashMap<>();

    public DefaultLogicFactory(List<IlogicFilter<?>> ilogicFilters) {
        for (IlogicFilter<?> ilogicFilter: ilogicFilters) {
            LogicStrategy logicStrategy = AnnotationUtils.findAnnotation(ilogicFilter.getClass(), LogicStrategy.class);
            if (Objects.nonNull(logicStrategy)) {
                logicFilterMap.put(logicStrategy.logicMode().code, ilogicFilter);
            }
        }
    }

    public <T extends RuleActionEntity.RaffleEntity> Map<String, IlogicFilter<T>> openLogicFilter() {
        return (Map<String, IlogicFilter<T>>) (Map<?,?>) logicFilterMap;
    }

    @Getter
    @AllArgsConstructor
    public enum LogicMode {

        RULE_WEIGHT("rule_weight", "【抽奖前规则】根据抽奖权重返回可抽奖KEY", "before"),

        RULE_BLACKLIST("rule_blacklist", "【抽奖前规则】黑名单规则过滤，命中黑名单直接返回", "before"),

        RULE_LOCK("rule_lock", "【抽奖中规则】抽奖n次后，对应奖品可解锁抽奖", "center"),

        RULE_LUCK_AWARD("rule_luck_award", "【抽奖后规则】抽奖n次后，对应奖品可解锁抽奖", "after");

        private final String code;

        private final String info;

        private final String type;

        public static boolean isCenter(String code) {
            return "center".equalsIgnoreCase(LogicMode.valueOf(code.toUpperCase()).type);
        }

        public static boolean isAfter(String code) {
            return "after".equalsIgnoreCase(LogicMode.valueOf(code.toUpperCase()).type);
        }

    }

}
