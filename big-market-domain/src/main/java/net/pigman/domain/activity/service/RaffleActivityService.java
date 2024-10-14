package net.pigman.domain.activity.service;

import net.pigman.domain.activity.model.aggregate.CreateOrderAggregate;
import net.pigman.domain.activity.model.entity.*;
import net.pigman.domain.activity.model.valobj.OrderStateVO;
import net.pigman.domain.activity.repository.IActivityRepository;
import net.pigman.domain.activity.service.rule.factory.DefaultActivityChainFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * packageName net.pigman.domain.activity.service
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivityService
 * @date 2024/10/7
 * @description 抽奖活动服务
 */
@Service
public class RaffleActivityService extends AbstractRaffleActivity {

    public RaffleActivityService(
            DefaultActivityChainFactory defaultActivityChainFactory, IActivityRepository activityRepository) {
        super(defaultActivityChainFactory, activityRepository);
    }

    @Override
    protected CreateOrderAggregate buildOrderAggregate(
            SkuRechargeEntity skuRechargeEntity, ActivitySkuEntity activitySkuEntity,
            ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {

        ActivityOrderEntity activityOrderEntity = new ActivityOrderEntity();
        activityOrderEntity.setUserId(skuRechargeEntity.getUserId());
        activityOrderEntity.setSku(skuRechargeEntity.getSku());
        activityOrderEntity.setActivityId(activityEntity.getActivityId());
        activityOrderEntity.setActivityName(activityEntity.getActivityName());
        activityOrderEntity.setStrategyId(activityEntity.getStrategyId());
        // 雪花
        activityOrderEntity.setOrderId(RandomStringUtils.randomNumeric(12));
        activityOrderEntity.setOrderTime(new Date());
        activityOrderEntity.setTotalCount(activityCountEntity.getTotalCount());
        activityOrderEntity.setDayCount(activityCountEntity.getDayCount());
        activityOrderEntity.setMonthCount(activityCountEntity.getMonthCount());
        activityOrderEntity.setState(OrderStateVO.completed);
        activityOrderEntity.setOutBusinessNo(skuRechargeEntity.getOutBusinessNo());

        return CreateOrderAggregate.builder()
                .userId(skuRechargeEntity.getUserId())
                .activityId(activitySkuEntity.getActivityId())
                .totalCount(activityCountEntity.getTotalCount())
                .dayCount(activityCountEntity.getDayCount())
                .monthCount(activityCountEntity.getMonthCount())
                .activityOrderEntity(activityOrderEntity)
                .build();
    }

    @Override
    protected void doSaveOrder(CreateOrderAggregate createOrderAggregate) {
        activityRepository.doSaveOrder(createOrderAggregate);
    }
}
