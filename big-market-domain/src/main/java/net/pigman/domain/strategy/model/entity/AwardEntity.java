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
 * @className AwardEntity
 * @date 2024/9/1
 * @description 策略结果实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AwardEntity {

    // 用户id
    private String userId;

    // 奖品id
    private Integer awardId;

}
