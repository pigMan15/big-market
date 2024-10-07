package net.pigman.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName net.pigman.domain.activity.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className OrderStateVO
 * @date 2024/10/7
 * @description 订单状态枚举值对象
 */
@Getter
@AllArgsConstructor
public enum OrderStateVO {

    completed("completed", "完成");

    private final String code;

    private final String desc;

}
