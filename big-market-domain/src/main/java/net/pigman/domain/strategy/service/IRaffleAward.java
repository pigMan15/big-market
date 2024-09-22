package net.pigman.domain.strategy.service;

import net.pigman.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

/**
 * packageName net.pigman.domain.strategy.service
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IRaffleAward
 * @date 2024/9/22
 * @description 策略奖品接口
 */
public interface IRaffleAward {

    /**
     * @description 查询抽奖策略对应奖品列表
     * @param strategyId:
     * return List<StrategyAwardEntity>
     * @author pig泉
     * @date 14:34 2024/9/22
     * {@link List<StrategyAwardEntity>}
     */
    List<StrategyAwardEntity> queryRaffleStrategyAwardList(Long strategyId);

}
