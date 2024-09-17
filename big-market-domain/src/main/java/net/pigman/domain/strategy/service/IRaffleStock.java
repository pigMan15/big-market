package net.pigman.domain.strategy.service;

import net.pigman.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

/**
 * packageName net.pigman.domain.strategy.service
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IRaffleStock
 * @date 2024/9/17
 * @description 抽奖库存相关
 */
public interface IRaffleStock {


    /**
     * @description 获取奖品库存消耗队列
     * @param :
     * return StrategyAwardStockKeyVO
     * @author pig泉
     * @date 10:15 2024/9/17
     * {@link StrategyAwardStockKeyVO}
     */
    StrategyAwardStockKeyVO takeQueueValue();


    /**
     * @description 更新奖品库存消耗记录
     * @param strategyId:
     * @param awardId:
     * return void
     * @author pig泉
     * @date 10:14 2024/9/17
     */
    void updateStrategyAwardStock(Long strategyId, Integer awardId);

}
