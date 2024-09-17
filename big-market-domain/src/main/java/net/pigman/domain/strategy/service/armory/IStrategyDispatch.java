package net.pigman.domain.strategy.service.armory;

/**
 * packageName net.pigman.domain.armory
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IStrategyArmory
 * @date 2024/9/1
 * @description 策略抽奖调度
 */
public interface IStrategyDispatch {

    /**
     * @description 获取抽奖策略装配后的随机结果
     * @param strategyId:
     * return Integer
     * @author pig泉
     * @date 22:04 2024/9/1
     * {@link Integer}
     */
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId, String ruleWeightValue);

    /**
     * @description 扣减奖品库存
     * @param strategyId:
     * @param awardId:
     * return Boolean
     * @author pig泉
     * @date 10:18 2024/9/17
     * {@link Boolean}
     */
    Boolean subtractionAwardStock(Long strategyId, Integer awardId);

}
