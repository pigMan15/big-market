package net.pigman.domain.strategy.service.armory;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.entity.StrategyAwardEntity;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * packageName net.pigman.domain.strategy.service.armory
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyArmory
 * @date 2024/9/1
 * @description 策略装配库，初始化策略计算
 */
@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory{


    @Resource
    private IStrategyRepository strategyRepository;


    /**
     * @description 装配抽奖策略配置，【触发时机为活动审核通过后触发】
     * @param :
     * return boolean
     * @author pig泉
     * @date 22:15 2024/9/1
     * {@link boolean}
     */
    @Override
    public boolean assembleLotteryStrategy(Long strategyId) {
        // 查询策略配置
        List<StrategyAwardEntity> strategyAwardEntityList = strategyRepository.queryStrategyAwardList(strategyId);

        // 获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntityList.stream().map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 获取概率值总和
        BigDecimal totalAwardRate = strategyAwardEntityList.stream().map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 计算概率氛围， 百分位，千分位，万分位
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        // 生成策略奖品概率查找表
        List<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for (StrategyAwardEntity strategyAward: strategyAwardEntityList) {
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            // 计算每种策略对应奖品所占的分量
            for (int i = 0; i < rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue(); i++) {
                strategyAwardSearchRateTables.add(awardId);
            }
        }

        // 重排序
        Collections.shuffle(strategyAwardSearchRateTables);

        // Map集合，key:概率值, value:奖品id
        Map<String, Integer> shuffleStrategyAwardRateTable = new LinkedHashMap<>();
        for (int i = 0; i < strategyAwardSearchRateTables.size(); i++) {
            shuffleStrategyAwardRateTable.put(String.valueOf(i), strategyAwardSearchRateTables.get(i));
        }

        // 缓存
        strategyRepository.storeStrategyAwardSearchRateTable(strategyId, shuffleStrategyAwardRateTable.size(), shuffleStrategyAwardRateTable);

        return true;
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        // 从redis获取最新的抽奖装配策略数据
        int rateRange = strategyRepository.getRateRange(strategyId);
        // 根据范围生成随机值，返回概率值查找表对应的奖品结果
        return strategyRepository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }
}
