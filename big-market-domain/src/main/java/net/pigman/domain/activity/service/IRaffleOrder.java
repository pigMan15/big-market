package net.pigman.domain.activity.service;

import net.pigman.domain.activity.model.entity.ActivityOrderEntity;
import net.pigman.domain.activity.model.entity.SkuRechargeEntity;

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


    /**
     * @description 创建sku账户充值订单，给用户增加抽奖次数
     * 1.【打卡，签到，分享，对话，积分兑换】等行为动作下，创建活动订单，给用户的活动账户【日，月】充值可用的抽奖次数
     * @param skuRechargeEntity:
     * return String
     * @author pig泉
     * @date 22:09 2024/10/10
     * {@link String}
     */
    String createSkuRechargeOrder(SkuRechargeEntity skuRechargeEntity);

}
