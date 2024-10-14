package net.pigman.domain.activity.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.entity.ActivityCountEntity;
import net.pigman.domain.activity.model.entity.ActivityEntity;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;
import net.pigman.domain.activity.service.rule.AbstractActionChain;
import org.springframework.stereotype.Component;

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

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-商品库存处理【校验&扣减】开始");
        return true;
    }

}
