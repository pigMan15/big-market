package net.pigman.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pigman.types.common.Constants;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * packageName net.pigman.domain.strategy.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyRuleEntity
 * @date 2024/9/2
 * @description 策略负责实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyRuleEntity {

    // 策略id
    private Long strategyId;

    // 奖品id
    private Integer awardId;

    // 规则类型(1:策略规则, 2:奖品规则)
    private Integer ruleType;

    // 规则类型(rule_random: 随机值计算， rule_lock：抽奖几次后解锁，rule_lock_award：幸运奖(兜底奖品))
    private String ruleModel;

    // 抽奖规则比值
    private String ruleValue;

    // 抽奖规则描述
    private String ruleDesc;

    public Map<String, List<Integer>> getRuleWeightValues() {
        if (!"rule_weight".equalsIgnoreCase(ruleModel)) {
            return null;
        }
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup: ruleValueGroups) {
            if (StringUtils.isEmpty(ruleValueGroup)) {
                return resultMap;
            }

            String[] parts = ruleValueGroup.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight value invalid format: " + ruleValueGroup);
            }

            String[] valueStrs = parts[1].split(Constants.SPLIT);
            List<Integer> values = Arrays.stream(valueStrs).map(v -> {
                return Integer.parseInt(v);
            }).collect(Collectors.toList());
            resultMap.put(ruleValueGroup, values);
        }
        return resultMap;
    }

}
