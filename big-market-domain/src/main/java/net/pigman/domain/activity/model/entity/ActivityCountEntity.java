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
 * @className ActivityCountEntity
 * @date 2024/10/7
 * @description 活动次数实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCountEntity {

    //  活动次数编号
    private Long activityCountId;

    // 总次数
    private Integer totalCount;

    // 日次数
    private Integer dayCount;

    // 月次数
    private Integer monthCount;

}
