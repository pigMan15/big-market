package net.pigman.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.pigman.domain.strategy.service.rule.factory.DefaultLogicFactory;
import net.pigman.types.common.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName net.pigman.domain.strategy.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyAwardRuleModelVO
 * @date 2024/9/7
 * @description 抽奖策略规则规则值对象
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {

    private String ruleModels;

    public String[] raffleCenterRuleModelList() {
        List<String> strs = Arrays.stream(ruleModels.split(Constants.SPLIT))
                .filter(DefaultLogicFactory.LogicMode::isCenter)
                .collect(Collectors.toList());
        return strs.toArray(new String[0]);
    }

    public String[] raffleAfterRuleModelList() {
        List<String> strs = Arrays.stream(ruleModels.split(Constants.SPLIT))
                .filter(DefaultLogicFactory.LogicMode::isAfter)
                .collect(Collectors.toList());
        return strs.toArray(new String[0]);
    }

}
