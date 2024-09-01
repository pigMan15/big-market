package net.pigman.domain.strategy.service.armory;

/**
 * packageName net.pigman.domain.armory
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IStrategyArmory
 * @date 2024/9/1
 * @description 策略装配库，初始化策略计算
 */
public interface IStrategyArmory {

    /**
     * @description 装配抽奖策略配置，【触发时机为活动审核通过后触发】
     * @param :
     * return boolean
     * @author pig泉
     * @date 22:03 2024/9/1
     * {@link boolean}
     */
    boolean assembleLotteryStrategy(Long strategyId);

    /**
     * @description 获取抽奖策略装配后的随机结果
     * @param strategyId:
     * return Integer
     * @author pig泉
     * @date 22:04 2024/9/1
     * {@link Integer}
     */
    Integer getRandomAwardId(Long strategyId);

}
