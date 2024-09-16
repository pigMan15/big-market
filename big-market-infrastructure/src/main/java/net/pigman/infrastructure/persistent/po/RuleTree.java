package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleTree
 * @date 2024/9/16
 * @description 规则树
 */
@Data
public class RuleTree {

    // id
    private Long id;

    // 规则树id
    private String treeId;

    // 规则树名称
    private String treeName;

    // 规则树描述
    private String treeDesc;

    // 规则树节点
    private String treeRootRuleKey;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
