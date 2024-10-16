package net.pigman.domain.activity.service.armory;

import java.util.Date;

/**
 * packageName net.pigman.domain.activity.service.armory
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IActivityDispatch
 * @date 2024/10/15
 * @description 活动调度-扣减库存
 */
public interface IActivityDispatch {

    /**
     * @description 扣减善品库存
     * @param sku:  活动sku
     * @param endDateTime: 活动结束时间，加锁key
     * return boolean
     * @author pig泉
     * @date 21:33 2024/10/15
     * {@link boolean}
     */
    boolean substractionActivitySkuStock(Long sku, Date endDateTime);

}
