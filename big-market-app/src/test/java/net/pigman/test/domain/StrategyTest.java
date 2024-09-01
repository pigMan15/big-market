package net.pigman.test.domain;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.service.armory.IStrategyArmory;
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

    @Test
    public void testStrategyArmory() {
        boolean result = strategyArmory.assembleLotteryStrategy(100002L);
        log.info("测试结果:{}", result);
    }

    @Test
    public void testGetAssembleRandomVal() {
        for (int i = 0; i < 6; i++) {
            Integer awardId = strategyArmory.getRandomAwardId(100002L);
            log.info("恭喜您抽中了{}奖品", awardId);
        }
    }

}
