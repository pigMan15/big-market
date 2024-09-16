package net.pigman.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

/**
 * packageName net.pigman.domain.strategy.model.valobj
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleTreeVO
 * @date 2024/9/14
 * @description 规则树对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeVO {

    // 规则树id
    private String treeId;

    // 规则树名称
    private String treeName;

    // 规则树描述
    private String treeDesc;

    // 规则树根节点
    private String treeRootRuleNode;

    // 规则节点集合
    private Map<String, RuleTreeNodeVO> treeNodeMap;

}
