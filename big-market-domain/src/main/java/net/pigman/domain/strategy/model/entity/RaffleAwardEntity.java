package net.pigman.domain.strategy.model.entity;

import lombok.*;

/**
 * packageName net.pigman.domain.strategy.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffileAwardEntity
 * @date 2024/9/5
 * @description 抽奖奖品实体
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {

    private Integer awardId;

    private String awardConfig;

    private Integer sort;

}
