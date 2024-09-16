package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleTreeNode
 * @date 2024/9/16
 * @description 规则树节点
 */
@Data
public class RuleTreeNode {

    // id
    private Long id;

    // 规则树id
    private String treeId;

    // 规则key
    private String ruleKey;

    // 规则描述
    private String ruleDesc;

    // 规则比值
    private String ruleValue;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
