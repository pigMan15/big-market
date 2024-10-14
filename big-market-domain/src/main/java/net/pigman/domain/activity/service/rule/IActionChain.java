package net.pigman.domain.activity.service.rule;

import net.pigman.domain.activity.model.entity.ActivityCountEntity;
import net.pigman.domain.activity.model.entity.ActivityEntity;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;

/**
 * packageName net.pigman.domain.activity.service.rule
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IActionChain
 * @date 2024/10/10
 * @description 下单规则过滤接口
 */
public interface IActionChain extends IActionChainArmory{

    boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

}
