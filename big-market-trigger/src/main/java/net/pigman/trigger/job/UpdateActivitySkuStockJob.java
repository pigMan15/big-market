package net.pigman.trigger.job;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.valobj.ActivitySkuStockKeyVO;
import net.pigman.domain.activity.service.ISkuStock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * packageName net.pigman.trigger.job
 *
 * @author pig泉
 * @version 1.0.0
 * @className UpdateActivitySkuStockJob
 * @date 2024/10/15
 * @description 更新活动sku数据库库存任务
 */
@Slf4j
@Component
public class UpdateActivitySkuStockJob {

    @Resource
    private ISkuStock skuStock;

    @Scheduled(cron = "0/5 * * * * ?")
    public void exec() {
        try {
            log.info("定时任务，更新活动sku库存");
            ActivitySkuStockKeyVO activitySkuStockKeyVO = skuStock.takeQueueValue();
            if (Objects.isNull(activitySkuStockKeyVO)) {
                return;
            }
            log.info("定时任务，更新活动sku库存开始，sku:{}，activityId:{}", activitySkuStockKeyVO.getSku(), activitySkuStockKeyVO.getActivityId());
            skuStock.updateActivitySkuStock(activitySkuStockKeyVO.getSku());
        } catch (Exception e) {
            log.error("定时任务，更新活动sku库存失败:{}", e.getMessage());
        }
    }

}
