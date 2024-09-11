package net.pigman.domain.strategy.service.rule.chain;

/**
 * packageName net.pigman.domain.strategy.service.rule.chain
 *
 * @author pig泉
 * @version 1.0.0
 * @interface ILogicChainArmory
 * @date 2024/9/11
 * @description 责任链装配
 */
public interface ILogicChainArmory {

    ILogicChain next();

    ILogicChain appendNext(ILogicChain next);

}
