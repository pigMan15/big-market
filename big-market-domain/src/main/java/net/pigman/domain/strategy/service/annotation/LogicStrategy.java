package net.pigman.domain.strategy.service.annotation;

import net.pigman.domain.strategy.service.rule.factory.DefaultLogicFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName net.pigman.domain.strategy.service.annotation
 *
 * @author pig泉
 * @version 1.0.0
 * @interface LogicStrategy
 * @date 2024/9/5
 * @description 策略枚举
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogicStrategy {

    DefaultLogicFactory.LogicMode logicMode();

}
