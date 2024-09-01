package net.pigman.domain.strategy.repository;

import net.pigman.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;
import java.util.Map;

/**
 * packageName net.pigman.domain.strategy.repository
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IStrategyRepository
 * @date 2024/9/1
 * @description 策略服务仓储接口
 */
public interface IStrategyRepository {

    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStrategyAwardSearchRateTable(Long strategyId, Integer rateRange, Map<String, Integer> strategyAwardSearchRateTable);

    Integer getStrategyAwardAssemble(Long strategyId, Integer rateKey);

    int getRateRange(Long strategyId);

}
