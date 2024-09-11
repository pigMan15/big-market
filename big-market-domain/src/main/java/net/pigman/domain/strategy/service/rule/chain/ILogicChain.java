package net.pigman.domain.strategy.service.rule.chain;

/**
 * packageName net.pigman.domain.strategy.service.rule.chain
 *
 * @author pig泉
 * @version 1.0.0
 * @interface ILogicChain
 * @date 2024/9/11
 * @description 责任链调用
 */
public interface ILogicChain extends ILogicChainArmory{

    Integer logic(String userId, Long strategyId);

}
