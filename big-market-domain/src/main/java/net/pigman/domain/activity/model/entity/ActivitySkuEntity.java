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
 * @className ActivitySkuEntity
 * @date 2024/10/7
 * @description 活动sku实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuEntity {

    // 商品sku
    private Long sku;

    // 活动id
    private Long activityId;

    // 活动个人参数id, 个人可以参加多少次活动(总，月，日次数)
    private Long activityCountId;

    // 库存总量
    private Integer stockCount;

    // 剩余库存量
    private Integer stockCountSurplus;

}
