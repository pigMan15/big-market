package net.pigman.domain.strategy.service;

import net.pigman.domain.strategy.model.entity.RaffleAwardEntity;
import net.pigman.domain.strategy.model.entity.RaffleFactorEntity;

/**
 * packageName net.pigman.domain.strategy.service
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IRaffleStrategy
 * @date 2024/9/5
 * @description 抽奖策略接口
 */
public interface IRaffleStrategy {

    /**
     * @description
     * @param raffleFactorEntity:
     * return RaffleAwardEntity
     * @author pig泉
     * @date 23:22 2024/9/5
     * {@link RaffleAwardEntity}
     */
    RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity);

}
