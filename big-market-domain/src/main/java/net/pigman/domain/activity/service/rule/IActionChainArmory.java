package net.pigman.domain.activity.service.rule;

/**
 * packageName net.pigman.domain.activity.service.rule
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IActionChainArmory
 * @date 2024/10/10
 * @description TODO
 */
public interface IActionChainArmory {

    IActionChain next();

    IActionChain appendNext(IActionChain next);

}
