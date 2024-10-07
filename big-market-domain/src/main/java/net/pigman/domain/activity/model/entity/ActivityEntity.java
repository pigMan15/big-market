package net.pigman.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pigman.domain.activity.model.valobj.ActivityStateVO;

import java.util.Date;

/**
 * packageName net.pigman.domain.activity.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivityEntity
 * @date 2024/10/7
 * @description 活动实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity {

    // 活动
    private Long activityId;

    // 活动名称
    private String activityName;

    // 活动描述
    private String activityDesc;

    // 开始时间
    private Date beginDateTime;

    // 结束时间
    private Date endDateTime;

    // 活动参数次数配置
    private Long activityCountId;

    // 抽奖策略id
    private Long strategyId;

    // 活动状态
    private ActivityStateVO state;

}
