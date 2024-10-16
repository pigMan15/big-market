package net.pigman.domain.activity.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.entity.ActivityCountEntity;
import net.pigman.domain.activity.model.entity.ActivityEntity;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;
import net.pigman.domain.activity.model.valobj.ActivityStateVO;
import net.pigman.domain.activity.service.rule.AbstractActionChain;
import net.pigman.types.enums.ResponseCode;
import net.pigman.types.exception.AppException;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        if (!ActivityStateVO.open.equals(activityEntity.getState())) {
            throw new AppException(ResponseCode.ACTIVITY_STATE_ERROR.getCode(), ResponseCode.ACTIVITY_STATE_ERROR.getInfo());
        }
        Date currentDate = new Date();
        if (activityEntity.getBeginDateTime().after(currentDate) || activityEntity.getEndDateTime().before(currentDate)) {
            throw new AppException(ResponseCode.ACTIVITY_DATE_ERROR.getCode(), ResponseCode.ACTIVITY_DATE_ERROR.getInfo());
        }
        if (activitySkuEntity.getStockCountSurplus() <= 0) {
            throw new AppException(ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getCode(), ResponseCode.ACTIVITY_SKU_STOCK_ERROR.getInfo());
        }
        return next().action(activitySkuEntity, activityEntity, activityCountEntity);
    }

}
