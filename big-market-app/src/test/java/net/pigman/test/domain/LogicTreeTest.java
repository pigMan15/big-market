package net.pigman.test.domain;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.valobj.*;
import net.pigman.domain.strategy.service.rule.tree.ILogicTreeNode;
import net.pigman.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import net.pigman.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * packageName net.pigman.test.domain
 *
 * @author pig泉
 * @version 1.0.0
 * @className LogicTreeTest
 * @date 2024/9/14
 * @description 规则树测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicTreeTest {

    @Resource
    private DefaultTreeFactory defaultTreeFactory;

    @Test
    public void testTreeRule(){

        RuleTreeNodeVO rule_lock = RuleTreeNodeVO.builder()
                .treeId(100000001)
                .ruleKey("rule_lock")
                .ruleDesc("限定用户完成N次抽奖后解锁")
                .ruleValue("1")
                .treeNodeLineVOList(
                        new ArrayList<RuleTreeNodeLineVO>() {{
                            add(RuleTreeNodeLineVO.builder()
                                    .treeId(100000001)
                                    .ruleNodeFrom("rule_lock")
                                    .ruleNodeTo("rule_luck_award")
                                    .ruleLimitType(RuleLimitTypeVO.EQUAL)
                                    .ruleLimitValue(RuleLogicCheckTypeVO.TAKE_OVER)
                                    .build());
                            add(RuleTreeNodeLineVO.builder()
                                    .treeId(100000001)
                                    .ruleNodeFrom("rule_lock")
                                    .ruleNodeTo("rule_stock")
                                    .ruleLimitType(RuleLimitTypeVO.EQUAL)
                                    .ruleLimitValue(RuleLogicCheckTypeVO.ALLOW)
                                    .build());
                        }}
                ).build();

        RuleTreeNodeVO rule_luck_award = RuleTreeNodeVO.builder()
                .treeId(100000001)
                .ruleKey("rule_luck_award")
                .ruleDesc("兜底奖品")
                .ruleValue("1")
                .treeNodeLineVOList(null)
                .build();

        RuleTreeNodeVO rule_stock = RuleTreeNodeVO.builder()
                .treeId(100000001)
                .ruleKey("rule_stock")
                .ruleDesc("库存处理规则")
                .ruleValue(null)
                .treeNodeLineVOList(new ArrayList<RuleTreeNodeLineVO>() {{
                    add(RuleTreeNodeLineVO.builder()
                            .treeId(100000001)
                            .ruleNodeFrom("rule_stock")
                            .ruleNodeTo("rule_luck_award")
                            .ruleLimitType(RuleLimitTypeVO.EQUAL)
                            .ruleLimitValue(RuleLogicCheckTypeVO.TAKE_OVER)
                            .build());
                }}).build();

        RuleTreeVO ruleTreeVO = new RuleTreeVO();
        ruleTreeVO.setTreeId(100000001);
        ruleTreeVO.setTreeName("决策树规则：抽奖");
        ruleTreeVO.setTreeDesc("决策树规则：抽奖");
        ruleTreeVO.setTreeRootRuleNode("rule_lock");
        ruleTreeVO.setTreeNodeMap(new HashMap<String, RuleTreeNodeVO>() {{
            put("rule_lock", rule_lock);
            put("rule_stock", rule_stock);
            put("rule_luck_award", rule_luck_award);
        }});

        IDecisionTreeEngine decisionTreeEngine = defaultTreeFactory.openLogicTree(ruleTreeVO);
        DefaultTreeFactory.StrategyAwardData strategyAwardData = decisionTreeEngine.process("pigman", 100001L, 100);
        log.info("测试结果:{}", JSON.toJSONString(strategyAwardData));

    }



}
