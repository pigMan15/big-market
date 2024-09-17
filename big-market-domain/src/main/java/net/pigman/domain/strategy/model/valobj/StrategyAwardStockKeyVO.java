package net.pigman.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName net.pigman.domain.strategy.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyAwardStockKeyVO
 * @date 2024/9/17
 * @description 策略奖品库存key标示值对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {

    private Long strategyId;

    private Integer awardId;

}
