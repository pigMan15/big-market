package net.pigman.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * packageName net.pigman.domain.strategy.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleTreeNodeLineVO
 * @date 2024/9/14
 * @description 规则树节点连线对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {

    // 规则树id
    private Integer treeId;

    // from节点
    private String ruleNodeFrom;

    // to节点
    private String ruleNodeTo;

    // 限定类型 1:= 2:> 3:< 4:>= 5:<= 6:enum【枚举范围】
    private RuleLimitTypeVO ruleLimitType;

    // 限定值（到下个节点）
    private RuleLogicCheckTypeVO ruleLimitValue;

}
