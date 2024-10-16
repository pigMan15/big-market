package net.pigman.domain.activity.repository;

import net.pigman.domain.activity.model.aggregate.CreateOrderAggregate;
import net.pigman.domain.activity.model.entity.ActivityCountEntity;
import net.pigman.domain.activity.model.entity.ActivityEntity;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;
import net.pigman.domain.activity.model.valobj.ActivitySkuStockKeyVO;

import java.util.Date;

/**
 * packageName net.pigman.domain.activity.repository
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IActivityRepository
 * @date 2024/10/7
 * @description 活动仓储接口
 */
public interface IActivityRepository {

    ActivitySkuEntity queryActivitySku(Long sku);

    ActivityEntity queryRaffleActivityByActivityId(Long activityId);

    ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId);

    void doSaveOrder(CreateOrderAggregate createOrderAggregate);

    void cacheActivitySkuStockCount(String cacheKey, Integer stockCount);

    boolean substractionActivitySkuStock(Long sku, String cacheKey, Date endDateTime);

    void activitySkuStockConsumeSendQueue(ActivitySkuStockKeyVO build);

    ActivitySkuStockKeyVO takeQueueValue();

    void clearQueueValue();

    void updateActivitySkuStock(Long sku);

    void clearActivitySkuStock(Long sku);
}
