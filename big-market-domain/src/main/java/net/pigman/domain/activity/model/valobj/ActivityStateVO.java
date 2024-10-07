package net.pigman.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName net.pigman.domain.activity.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className ActivityStateVO
 * @date 2024/10/7
 * @description 活动状态枚举值对象
 */
@Getter
@AllArgsConstructor
public enum ActivityStateVO {

    create("create", "创建");

    private final String code;

    private final String desc;

}
