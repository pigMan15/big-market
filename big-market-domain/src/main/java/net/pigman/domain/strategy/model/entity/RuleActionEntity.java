package net.pigman.domain.strategy.model.entity;

import lombok.*;
import net.pigman.domain.strategy.model.valobj.RuleLogicCheckTypeVO;

/**
 * packageName net.pigman.domain.strategy.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleActionEntity
 * @date 2024/9/5
 * @description 规则动作实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleActionEntity<T extends RuleActionEntity.RaffleEntity> {

    private String code = RuleLogicCheckTypeVO.ALLOW.getCode();

    private String info = RuleLogicCheckTypeVO.ALLOW.getInfo();

    private String ruleModel;

    private T data;

    static public class RaffleEntity {

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static public class RaffleBeforeEntity extends RaffleEntity {

        // 策略id
        private Long strategyId;

        // 权重值key
        private String ruleWeightValueKey;

        // 奖品id
        private Integer awardId;

    }

    static public class RaffleCenterEntity extends RaffleEntity {

    }

    static public class RaffleAfterEntity extends RaffleEntity {

    }


}
