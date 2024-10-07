package net.pigman.test.domain.strategy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.service.rule.chain.ILogicChain;
import net.pigman.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import net.pigman.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;

/**
 * packageName net.pigman.test.domain
 *
 * @author pig泉
 * @version 1.0.0
 * @className LogicChainTest
 * @date 2024/9/11
 * @description 责任链测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogicChainTest {

    @Resource
    private DefaultChainFactory defaultChainFactory;

    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;

    /**
     * @description 黑名单测试
     * @param :
     * return void
     * @author pig泉
     * @date  2024/9/11
     * {@link}
     */
    @Test
    public void testLogicChainRuleBlackList() {

        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO strategyAwardVO = logicChain.logic("user001", 100001L);
        log.info("测试结果:{}", JSON.toJSONString(strategyAwardVO));

    }

    @Test
    public void testLogicChainRuleWeight() {
        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore",  4500L);
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100001L);
        DefaultChainFactory.StrategyAwardVO strategyAwardVO = logicChain.logic("pigman", 100001L);
        log.info("测试结果:{}", JSON.toJSONString(strategyAwardVO));
    }

    @Test
    public void testLogicChainDefault() {
        ILogicChain logicChain = defaultChainFactory.openLogicChain(100002L);
        DefaultChainFactory.StrategyAwardVO strategyAwardVO = logicChain.logic("pigman", 100002L);
        log.info("测试结果:{}", JSON.toJSONString(strategyAwardVO));
    }

}
