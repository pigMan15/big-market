package net.pigman.domain.activity.model.entity;

import lombok.Data;

/**
 * packageName net.pigman.domain.activity.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivityShopCartEntity
 * @date 2024/10/7
 * @description 活动商品充值实体对象
 */
@Data
public class SkuRechargeEntity {

    // 用户id
    private String userId;

    // 商品sku: activity + activity count
    private Long sku;

    // 幂等业务单号，结果唯一，不会重复充值
    private String outBusinessNo;

}
