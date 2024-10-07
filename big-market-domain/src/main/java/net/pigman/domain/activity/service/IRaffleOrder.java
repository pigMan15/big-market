package net.pigman.domain.activity.service;

import net.pigman.domain.activity.model.entity.ActivityOrderEntity;
import net.pigman.domain.activity.model.entity.ActivityShopCartEntity;

/**
 * packageName net.pigman.domain.activity.service
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IRaffleOrder
 * @date 2024/10/7
 * @description 抽奖活动订单接口
 */
public interface IRaffleOrder {


    // sku创建活动订单，获得参与抽奖资格
    ActivityOrderEntity createRaffleActivityOrder(ActivityShopCartEntity activityShopCartEntity);

}
