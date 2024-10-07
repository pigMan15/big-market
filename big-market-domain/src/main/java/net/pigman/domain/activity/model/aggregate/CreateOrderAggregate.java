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

    // 活动账户实体
    private ActivityAccountEntity activityAccountEntity;

    // 活动订单实体
    private ActivityOrderEntity activityOrderEntity;

}
