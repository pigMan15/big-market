package net.pigman.domain.activity.service;

import net.pigman.domain.activity.model.entity.ActivityCountEntity;
import net.pigman.domain.activity.model.entity.ActivityEntity;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;
import net.pigman.domain.activity.repository.IActivityRepository;
import net.pigman.domain.activity.service.rule.factory.DefaultActivityChainFactory;

/**
 * packageName net.pigman.domain.activity.service
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivitySupport
 * @date 2024/10/10
 * @description 抽奖活动的支撑类
 */
public class RaffleActivitySupport {

    protected DefaultActivityChainFactory defaultActivityChainFactory;

    protected IActivityRepository activityRepository;

    public RaffleActivitySupport(DefaultActivityChainFactory defaultActivityChainFactory, IActivityRepository activityRepository) {
        this.defaultActivityChainFactory = defaultActivityChainFactory;
        this.activityRepository = activityRepository;
    }

    /**
     * @description 通过sku查询活动信息
     * @param null:
     * return null
     * @author pig泉
     * @date 22:52 2024/10/10
     * {@link null}
     */
    public ActivitySkuEntity queryActivitySku(Long sku) {
        return activityRepository.queryActivitySku(sku);
    }

    /**
     * @description 查询活动信息
     * @param activityId:
     * return ActivityEntity
     * @author pig泉
     * @date 22:52 2024/10/10
     * {@link ActivityEntity}
     */
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        return activityRepository.queryRaffleActivityByActivityId(activityId);
    }

    /**
     * @description 查询次数信息
     * @param activityCountId:
     * return ActivityCountEntity
     * @author pig泉
     * @date 22:52 2024/10/10
     * {@link ActivityCountEntity}
     */
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        return activityRepository.queryRaffleActivityCountByActivityCountId(activityCountId);
    }

}
