package net.pigman.domain.strategy.repository;

import net.pigman.domain.strategy.model.entity.StrategyAwardEntity;
import net.pigman.domain.strategy.model.entity.StrategyEntity;
import net.pigman.domain.strategy.model.entity.StrategyRuleEntity;
import net.pigman.domain.strategy.model.valobj.RuleTreeVO;
import net.pigman.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import net.pigman.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

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

    void storeStrategyAwardSearchRateTable(String strategyId, Integer rateRange, Map<String, Integer> strategyAwardSearchRateTable);

    Integer getStrategyAwardAssemble(String key, Integer rateKey);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);

    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModelVO(Long strategyId, Integer awardId);

    String queryStrategyRuleValue(Long strategyId, String ruleModel);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    void cacheStrategyAwardCount(String cacheKey, Integer awardCount);

    Boolean subtractionAwardStock(String cacheKey);

    /**
     * @description 生产奖品库存信息到消费队列
     * @param strategyAwardStockKeyVO:
     * return void
     * @author pig泉
     * @date 10:46 2024/9/17
     */
    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    /**
     * @description 从消费队列中消费奖品库存信息
     * @param :
     * return StrategyAwardStockKeyVO
     * @author pig泉
     * @date 10:47 2024/9/17
     * {@link StrategyAwardStockKeyVO}
     */
    StrategyAwardStockKeyVO takeQueueValue();

    /**
     * @description 更新奖品库存消耗记录
     * @param strategyId:
     * @param awardId:
     * return void
     * @author pig泉
     * @date 10:48 2024/9/17
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);

    /**
     * @description 查询奖品信息
     * @param strategyId:
     * @param awardId:
     * return StrategyAwardEntity
     * @author pig泉
     * @date 15:18 2024/9/22
     * {@link StrategyAwardEntity}
     */
    StrategyAwardEntity queryStrategyAwardEntity(Long strategyId, Integer awardId);

}


