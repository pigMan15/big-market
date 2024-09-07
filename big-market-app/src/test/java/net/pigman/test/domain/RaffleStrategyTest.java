package net.pigman.test.domain;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.RaffleAwardEntity;
import net.pigman.domain.strategy.model.entity.RaffleFactorEntity;
import net.pigman.domain.strategy.service.IRaffleStrategy;
import net.pigman.domain.strategy.service.rule.impl.RuleWeightLogicFilter;
import org.junit.Before;
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
 * @className RaffleStrategyTest
 * @date 2024/9/7
 * @description 抽奖规则测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleStrategyTest {

    @Resource
    private IRaffleStrategy raffleStrategy;

    @Resource
    private RuleWeightLogicFilter ruleWeightLogicFilter;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(ruleWeightLogicFilter, "userScore", 4050L);
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
                .strategyId(100001l)
                .build();
        RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(raffleFactorEntity);
        log.info("请求参数:{}, 请求结果:{}", JSON.toJSONString(raffleFactorEntity), JSON.toJSONString(raffleAwardEntity));
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


}
