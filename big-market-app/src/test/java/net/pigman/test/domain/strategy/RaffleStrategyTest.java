package net.pigman.test.domain.strategy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RaffleAwardEntity;
import net.pigman.domain.strategy.model.entity.RaffleFactorEntity;
import net.pigman.domain.strategy.service.IRaffleStrategy;
import net.pigman.domain.strategy.service.armory.IStrategyArmory;
import net.pigman.domain.strategy.service.rule.chain.impl.RuleWeightLogicChain;
import net.pigman.domain.strategy.service.rule.filter.impl.RuleLockLogicFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * packageName net.pigman.test.domain
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleStrategyTest
 * @date 2024/9/7
 * @description 抽奖规则测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyTest {

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private IRaffleStrategy raffleStrategy;

    @Resource
    private RuleWeightLogicChain ruleWeightLogicChain;

    @Resource
    private RuleLockLogicFilter ruleLockLogicFilter;

    @Before
    public void setUp() {

        // 策略装配
//        log.info("测试结果:{}", strategyArmory.assembleLotteryStrategy(100001l));
        log.info("测试结果:{}", strategyArmory.assembleLotteryStrategy(100006l));

        ReflectionTestUtils.setField(ruleWeightLogicChain, "userScore", 4050L);

//        ReflectionTestUtils.setField(ruleLockLogicFilter, "userRaffleCount", 10L);

    }

    /**
     * @description 权重规则过滤单元测试
     * @param :
     * return void
     * @author pig泉
     * @date 16:02 2024/9/7
     */
    @Test
    public void testPerformRaffle() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("pigman")
                .strategyId(100006l)
                .build();
        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);
        log.info("请求参数:{}, 请求结果:{}", JSON.toJSONString(raffleFactorEntity), JSON.toJSONString(raffleAwardEntity));
    }

    @Test
    public void testPerFormRaffleStock() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                    .userId("pigman")
                    .strategyId(100006l)
                    .build();
            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);
            log.info("请求参数:{}, 请求结果:{}", JSON.toJSONString(raffleFactorEntity), JSON.toJSONString(raffleAwardEntity));
        }
        new CountDownLatch(1).await();
    }

    /**
     * @description 黑名单规则过滤单元测试
     * @param :
     * return void
     * @author pig泉
     * @date 16:04 2024/9/7
     */
    @Test
    public void testPerformRaffleBlackList() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder()
                .userId("user001")
                .strategyId(100001l)
                .build();
        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);
        log.info("请求参数:{}, 请求结果:{}", JSON.toJSONString(raffleFactorEntity), JSON.toJSONString(raffleAwardEntity));
    }

    /**
     * @description 次数锁校验单元测试，抽奖n次后解锁奖品
     * @param :
     * return void
     * @author pig泉
     * @date 18:58 2024/9/7
     */
    @Test
    public void testPerformRaffleCenterRuleLock() {
        RaffleFactorEntity raffleFactorEntity = RaffleFactorEntity.builder().userId("pigman").strategyId(100003l).build();
        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);
        log.info("请求参数:{}, 请求结果:{}", JSON.toJSONString(raffleFactorEntity), JSON.toJSONString(raffleAwardEntity));
    }


}
