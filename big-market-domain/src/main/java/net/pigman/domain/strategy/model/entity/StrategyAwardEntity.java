package net.pigman.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * packageName net.pigman.domain.strategy.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyAwardEntity
 * @date 2024/9/1
 * @description 策略奖品实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardEntity {

    // 抽奖策略id
    private Long strategyId;

    // 奖品id
    private Integer awardId;

    // 奖品标题
    private String awardTitle;

    // 奖品副标题
    private String awardSubtitle;

    // 奖品库存数量
    private Integer awardCount;

    // 奖品库存剩余
    private Integer awardCountSurplus;

    // 奖品中奖概率
    private BigDecimal awardRate;

    // 排序
    private Integer sort;

}
