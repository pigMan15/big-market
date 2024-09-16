package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className RuleTreeNodeLine
 * @date 2024/9/16
 * @description 规则树节点线
 */
@Data
public class RuleTreeNodeLine {

    // id
    private Long id;

    // 规则树id
    private String treeId;

    // from 节点
    private String ruleNodeFrom;

    // to 节点
    private String ruleNodeTo;

    // 限定类型
    private String ruleLimitType;

    // 限定值
    private String ruleLimitValue;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
