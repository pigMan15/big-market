package net.pigman.test.domain.activity;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.entity.ActivityOrderEntity;
import net.pigman.domain.activity.model.entity.SkuRechargeEntity;
import net.pigman.domain.activity.service.IRaffleOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * packageName net.pigman.test.domain.activity
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleOrderTest
 * @date 2024/10/7
 * @description 抽奖活动订单单元测试
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleOrderTest {

    @Resource
    private IRaffleOrder raffleOrder;

    @Test
    public void testCreateRaffleActivityOrder() {
        SkuRechargeEntity activityShopCartEntity = new SkuRechargeEntity();
        activityShopCartEntity.setUserId("pigman");
        activityShopCartEntity.setSku(9011L);
//        ActivityOrderEntity raffleActivityOrder = raffleOrder.createRaffleActivityOrder(activityShopCartEntity);
//        log.info("测试结果:{}", JSON.toJSONString(raffleActivityOrder));
    }

    @Test
    public void testCreateSkuChargeOrder() {
        SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
        skuRechargeEntity.setUserId("pigman");
        skuRechargeEntity.setSku(9011L);
        skuRechargeEntity.setOutBusinessNo("700091009116");
        String orderId = raffleOrder.createSkuRechargeOrder(skuRechargeEntity);
        log.info("测试结果:{}", orderId);
    }

}
