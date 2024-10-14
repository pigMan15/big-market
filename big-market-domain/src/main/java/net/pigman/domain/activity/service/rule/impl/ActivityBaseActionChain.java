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
 * @className ActivityBaseActionChain
 * @date 2024/10/10
 * @description TODO
 */
@Slf4j
@Component("activity_base_action")
public class ActivityBaseActionChain extends AbstractActionChain {

    @Override
    public boolean action(ActivitySkuEntity activitySkuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        log.info("活动责任链-基础信息【有效期，状态】校验开始");
        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }

}
