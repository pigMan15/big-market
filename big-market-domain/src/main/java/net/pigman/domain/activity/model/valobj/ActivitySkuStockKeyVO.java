package net.pigman.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName net.pigman.domain.activity.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivitySkuStockKeyVO
 * @date 2024/10/15
 * @description 活动sku库存key值对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivitySkuStockKeyVO {

    // 商品sku
    private Long sku;

    // 活动id
    private Long activityId;

}
