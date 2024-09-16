package net.pigman.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * packageName net.pigman.domain.strategy.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleTreeNodeVO
 * @date 2024/9/14
 * @description 规则树节点对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeVO {

    // 规则树id
    private String treeId;

    // 规则key
    private String ruleKey;

    // 规则描述
    private String ruleDesc;

    // 规则比值
    private String ruleValue;

    // 规则连线集合
    private List<RuleTreeNodeLineVO> treeNodeLineVOList;

}
