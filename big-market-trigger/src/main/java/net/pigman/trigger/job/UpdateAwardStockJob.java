package net.pigman.trigger.job;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import net.pigman.domain.strategy.service.IRaffleStock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * packageName net.pigman.trigger.job
 *
 * @author pig泉
 * @version 1.0.0
 * @className UpdateAwardStockJob
 * @date 2024/9/17
 * @description 奖品库存队列消费定时任务
 */
@Slf4j
@Component
public class UpdateAwardStockJob {

    @Resource
    private IRaffleStock raffleStock;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        try {
            log.info("定时任务，更新奖品库存消耗，消费消息");
            // 从队列消费奖品库存消息
            StrategyAwardStockKeyVO strategyAwardStockKeyVO = raffleStock.takeQueueValue();
            if (Objects.isNull(strategyAwardStockKeyVO)) {
                return;
            }
            log.info("定时任务，更新奖品库存消耗, strategyId:{}, awardId:{}", strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
            // 更新数据库信息
            raffleStock.updateStrategyAwardStock(strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
        } catch (Exception e) {
            log.error("定时任务，更新奖品库存消耗失败, 异常信息:{}", e.getMessage());
        }
    }

}
