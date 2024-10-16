package net.pigman.domain.activity.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.entity.ActivityCountEntity;
import net.pigman.domain.activity.model.entity.ActivityEntity;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;
import net.pigman.domain.activity.model.valobj.ActivitySkuStockKeyVO;
import net.pigman.domain.activity.repository.IActivityRepository;
import net.pigman.domain.activity.service.armory.IActivityDispatch;
import net.pigman.domain.activity.service.rule.AbstractActionChain;
import net.pigman.types.enums.ResponseCode;
import net.pigman.types.exception.AppException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * packageName net.pigman.domain.activity.service.rule.impl
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivitySkuStockActionChain
 * @date 2024/10/10
 * @description TODO
 */
@Slf4j
@Component("activity_sku_stock_action")
public class ActivitySkuStockActionChain extends AbstractActionChain {

    @Resource
    private IActivityDispatch activityDispatch;

    @Resource
    private IActivityRepository activityRepository;

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-商品库存处理【校验&扣减】开始");
        // 扣减库存
        boolean status = activityDispatch.substractionActivitySkuStock(activitySkuEntity.getSku(), activityEntity.getEndDateTime());
        if (status) {
            log.info("活动责任链-商品库存处理【校验&扣减】成功, sku:{}, activityId:{}", activitySkuEntity.getSku(), activityEntity.getActivityId());
            // 写入延迟队列，延迟更新数据库库存
            activityRepository.activitySkuStockConsumeSendQueue(
                    ActivitySkuStockKeyVO.builder()
                            .sku(activitySkuEntity.getSku())
                            .activityId(activityEntity.getActivityId())
                            .build());
            return true;
        }
        throw new AppException(ResponseCode.ACTIVITY_SKU_STOCK_ERROR);
    }

}
