package net.pigman.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName net.pigman.domain.strategy.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleFactorEntity
 * @date 2024/9/5
 * @description 抽奖因子实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {

    private String userId;

    private Long strategyId;

    private Integer awardId;

}
