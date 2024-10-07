package net.pigman.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName net.pigman.domain.activity.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivityAccountEntity
 * @date 2024/10/7
 * @description 活动账户实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityAccountEntity {

    // 用户
    private String userId;

    // 活动
    private Long activityId;

    // 总次数
    private Integer totalCount;

    // 剩余次数
    private Integer totalCountSurplus;

    // 日次数
    private Integer dayCount;

    // 日剩余次数
    private Integer dayCountSurplus;

    // 月次数
    private Integer monthCount;

    // 月剩余次数
    private Integer monthCountSurplus;

}
