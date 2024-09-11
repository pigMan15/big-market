package net.pigman.domain.strategy.service.rule.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName net.pigman.domain.strategy.service.rule.chain
 *
 * @author pig泉
 * @version 1.0.0
 * @className AbstractLogicChain
 * @date 2024/9/11
 * @description 抽奖策略责任链
 */
@Slf4j
public abstract class AbstractLogicChain implements ILogicChain{

    private ILogicChain next;

    @Override
    public ILogicChain next() {
        return next;
    }

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    protected abstract String ruleModel();

}
