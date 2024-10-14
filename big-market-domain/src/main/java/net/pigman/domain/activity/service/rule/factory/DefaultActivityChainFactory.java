package net.pigman.domain.activity.service.rule.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.service.rule.IActionChain;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * packageName net.pigman.domain.activity.service.rule.factory
 *
 * @author pig泉
 * @version 1.0.0
 * @className DefaultActivityChainFactory
 * @date 2024/10/10
 * @description TODO
 */
@Slf4j
@Service
public class DefaultActivityChainFactory {

    private final IActionChain actionChain;

    public DefaultActivityChainFactory(Map<String, IActionChain> actionChainGroup) {
        actionChain = actionChainGroup.get(ActionModel.activity_base_action.getCode());
        actionChain.appendNext(actionChainGroup.get(ActionModel.activity_sku_stock_action.getCode()));
    }

    public IActionChain openActionChain() {
        return actionChain;
    }

    @Getter
    @AllArgsConstructor
    public enum ActionModel {

        activity_base_action("activity_base_action", "活动的库存，时间校验"),

        activity_sku_stock_action("activity_sku_stock_action", "活动sku库存校验");

        private final String code;

        private final String info;

    }

}
