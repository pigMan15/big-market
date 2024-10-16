package net.pigman.domain.activity.service;

import net.pigman.domain.activity.model.valobj.ActivitySkuStockKeyVO;

/**
 * packageName net.pigman.domain.activity.service
 *
 * @author pig泉
 * @version 1.0.0
 * @interface ISkuStock
 * @date 2024/10/15
 * @description 活动sku库存处理接口
 */
public interface ISkuStock {

    /**
     * @description 消费活动sku库存队列数据
     * @param :
     * return ActivitySkuStockKeyVO
     * @author pig泉
     * @date 22:45 2024/10/15
     * {@link ActivitySkuStockKeyVO}
     */
    ActivitySkuStockKeyVO takeQueueValue();

    /**
     * @description 清空活动sku库存队列数据
     * @param :
     * return void
     * @author pig泉
     * @date 2024/10/15
     */
    void clearQueueValue();

    /**
     * @description 通过延迟队列消费，缓慢更新活动sku数据库库存数据
     * @param :
     * return void
     * @author pig泉
     * @date 22:47 2024/10/15
     */
    void updateActivitySkuStock(Long sku);

    /**
     * @description 清空数据库库存数据
     * @param :
     * return void
     * @author pig泉
     * @date 22:48 2024/10/15
     */
    void clearActivitySkuStock(Long sku);

}
