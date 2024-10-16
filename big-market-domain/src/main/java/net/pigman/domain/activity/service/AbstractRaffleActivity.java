package net.pigman.domain.activity.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.aggregate.CreateOrderAggregate;
import net.pigman.domain.activity.model.entity.*;
import net.pigman.domain.activity.repository.IActivityRepository;
import net.pigman.domain.activity.service.rule.IActionChain;
import net.pigman.domain.activity.service.rule.factory.DefaultActivityChainFactory;
import net.pigman.types.enums.ResponseCode;
import net.pigman.types.exception.AppException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * packageName net.pigman.domain.activity.service
 *
 * @author pig泉
 * @version 1.0.0
 * @className AbstractRaffleActivity
 * @date 2024/10/7
 * @description 抽奖活动抽象类，定义标准流程
 */
@Slf4j
public abstract class AbstractRaffleActivity extends RaffleActivitySupport implements IRaffleOrder{

    public AbstractRaffleActivity(DefaultActivityChainFactory defaultActivityChainFactory, IActivityRepository activityRepository) {
        super(defaultActivityChainFactory, activityRepository);
    }

    @Override
    public String createSkuRechargeOrder(SkuRechargeEntity skuRechargeEntity) {

        // 参数校验
        Long sku = skuRechargeEntity.getSku();
        String userId = skuRechargeEntity.getUserId();
        String outBusinessNo = skuRechargeEntity.getOutBusinessNo();
        if (Objects.isNull(sku) || StringUtils.isEmpty(userId) || StringUtils.isEmpty(outBusinessNo)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 查询基础信息
        ActivitySkuEntity activitySkuEntity = queryActivitySku(sku);
        ActivityEntity activityEntity = queryRaffleActivityByActivityId(activitySkuEntity.getActivityId());
        ActivityCountEntity activityCountEntity = queryRaffleActivityCountByActivityCountId(activitySkuEntity.getActivityCountId());

        // 活动规则校验
        IActionChain actionChain = defaultActivityChainFactory.openActionChain();
        actionChain.action(activitySkuEntity, activityEntity, activityCountEntity);

        // 创建活动订单聚合对象
        CreateOrderAggregate createOrderAggregate = buildOrderAggregate(skuRechargeEntity, activitySkuEntity, activityEntity, activityCountEntity);

        // 保存订单
        doSaveOrder(createOrderAggregate);

        // 返回单号
        return createOrderAggregate.getActivityOrderEntity().getOrderId();

    }

    protected abstract CreateOrderAggregate buildOrderAggregate(
            SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity,
            ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

    protected abstract void doSaveOrder(CreateOrderAggregate createOrderAggregate);

}
