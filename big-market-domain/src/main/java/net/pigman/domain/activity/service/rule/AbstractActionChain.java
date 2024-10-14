package net.pigman.domain.activity.service.rule;

import net.pigman.domain.activity.model.entity.ActivityCountEntity;
import net.pigman.domain.activity.model.entity.ActivityEntity;
import net.pigman.domain.activity.model.entity.ActivitySkuEntity;

/**
 * packageName net.pigman.domain.activity.service.rule
 *
 * @author pig泉
 * @version 1.0.0
 * @className AbstractActionChain
 * @date 2024/10/10
 * @description 下单规则责任链抽象类
 */
public abstract class AbstractActionChain implements IActionChain {

    private IActionChain next;

    @Override
    public IActionChain next() {
        return next;
    }

    @Override
    public IActionChain appendNext(IActionChain next) {
        this.next = next;
        return next;
    }
}
