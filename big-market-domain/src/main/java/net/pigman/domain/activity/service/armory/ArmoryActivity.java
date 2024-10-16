package net.pigman.domain.activity.service.armory;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;
import net.pigman.domain.activity.repository.IActivityRepository;
import net.pigman.types.common.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * packageName net.pigman.domain.activity.service.armory
 *
 * @author pig泉
 * @version 1.0.0
 * @className ArmoryActivity
 * @date 2024/10/15
 * @description 活动sku预热
 */
@Slf4j
@Service
public class ArmoryActivity implements IActivityArmory, IActivityDispatch {

    @Resource
    private IActivityRepository activityRepository;

    @Override
    public boolean assembleActivitySku(Long sku) {
        // 预热活动sku库存
        ActivitySkuEntity activitySkuEntity = activityRepository.queryActivitySku(sku);
        cacheActivitySkuStockCount(sku, activitySkuEntity.getStockCount());

        // 查询活动，预热数据至缓存
        activityRepository.queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());

        // 查询活动次数，预热数据至缓存
        activityRepository.queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());

        return true;
    }


    private void cacheActivitySkuStockCount(Long sku, Integer stockCount) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        activityRepository.cacheActivitySkuStockCount(cacheKey, stockCount);
    }

    @Override
    public boolean substractionActivitySkuStock(Long sku, Date endDateTime) {
        String cacheKey = Constants.RedisKey.ACTIVITY_SKU_STOCK_COUNT_KEY + sku;
        return activityRepository.substractionActivitySkuStock(sku, cacheKey, endDateTime);
    }
}
