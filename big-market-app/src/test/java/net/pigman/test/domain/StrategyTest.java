package net.pigman.test.domain;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.service.armory.IStrategyArmory;
import net.pigman.domain.strategy.service.armory.IStrategyDispatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * packageName net.pigman.test.domain
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyTest
 * @date 2024/9/1
 * @description 策略领域但愿测试方法类
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private IStrategyDispatch strategyDispatch;

    @Test
    public void testStrategyArmory() {
        boolean result = strategyArmory.assembleLotteryStrategy(100001L);
        boolean result2 = strategyArmory.assembleLotteryStrategy(100002L);
        boolean result3 = strategyArmory.assembleLotteryStrategy(100003L);
        log.info("测试结果:{}", result);
    }

    @Test
    public void testGetAssembleRandomVal() {
        for (int i = 0; i < 6; i++) {
            Integer awardId = strategyDispatch.getRandomAwardId(100002L);
            log.info("恭喜您抽中了{}奖品", awardId);
        }
    }

    @Test
    public void testGetRandomAwardIdRuleWeightValue() {
        log.info("测试结果:{} - 4000 策略配置", strategyDispatch.getRandomAwardId(100001L, "4000:102,103,104,105"));
        log.info("测试结果:{} - 5000 策略配置", strategyDispatch.getRandomAwardId(100001L, "5000:102,103,104,105,106,107"));
        log.info("测试结果:{} - 6000 策略配置", strategyDispatch.getRandomAwardId(100001L, "6000:102,103,104,105,106,107,108,109"));
    }

}
