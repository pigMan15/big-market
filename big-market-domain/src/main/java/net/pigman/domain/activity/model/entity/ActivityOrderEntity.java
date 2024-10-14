package net.pigman.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.pigman.domain.activity.model.valobj.OrderStateVO;

import java.util.Date;

/**
 * packageName net.pigman.domain.activity.model.entity
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivityOrderEntity
 * @date 2024/10/7
 * @description 活动订单实体对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityOrderEntity {

    // 用户id
    private String userId;

    // sku
    private Long sku;

    // 活动id
    private Long activityId;

    // 活动名称
    private String activityName;

    // 抽奖策略id
    private Long strategyId;

    // 订单id
    private String orderId;

    // 下单时间
    private Date orderTime;

    // 总次数
    private Integer totalCount;

    // 日次数
    private Integer dayCount;

    // 月次数
    private Integer monthCount;

    // 订单状态
    private OrderStateVO state;

    // 业务外置id
    private String outBusinessNo;

}
