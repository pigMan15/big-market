package net.pigman.domain.activity.model.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pigman.domain.activity.model.entity.ActivityAccountEntity;
import net.pigman.domain.activity.model.entity.ActivityOrderEntity;

/**
 * packageName net.pigman.domain.activity.model.aggregate
 *
 * @author pig泉
 * @version 1.0.0
 * @className CreateOrderAggregate
 * @date 2024/10/7
 * @description 下单聚合对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderAggregate {

    // 用户
    private String userId;

    // 活动id
    private Long activityId;

    // 增加-总次数
    private Integer totalCount;

    // 增加-日次数
    private Integer dayCount;

    // 增加-月次数
    private Integer monthCount;

    // 活动订单实体
    private ActivityOrderEntity activityOrderEntity;

}
