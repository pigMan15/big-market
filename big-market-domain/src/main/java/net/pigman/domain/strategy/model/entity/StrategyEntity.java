package net.pigman.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pigman.types.common.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * packageName net.pigman.domain.strategy.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyEntity
 * @date 2024/9/2
 * @description 策略实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {

    private Long strategyId;

    private String strategyDesc;

    private String ruleModels;

    public String[] ruleModels() {
        if (StringUtils.isBlank(ruleModels)) {
            return new String[]{};
        } else {
            return ruleModels.split(Constants.SPLIT);
        }
    }

    public String getRuleWeight() {
        String[] ruleModelsTmp = this.ruleModels();
        for (String ruleModel: ruleModelsTmp) {
            if ("rule_weight".equalsIgnoreCase(ruleModel)) {
                return ruleModel;
            }
        }
        return null;
    }

}
