package net.pigman.test.domain.activity;

import lombok.extern.slf4j.Slf4j;
import net.pigman.domain.activity.model.entity.SkuRechargeEntity;
import net.pigman.domain.activity.service.IRaffleOrder;
import net.pigman.domain.activity.service.armory.IActivityArmory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

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

    @Resource
    private IActivityArmory activityArmory;

    @Before
    public void setup() {
        log.info("装配活动:{}", activityArmory.assembleActivitySku(9011L));
    }

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

    @Test
    public void testCreateSkuRechargeOrderV2() throws InterruptedException {
        try {
            for (int i=0; i<20; i++) {
                SkuRechargeEntity skuRechargeEntity = new SkuRechargeEntity();
                skuRechargeEntity.setUserId("pigman");
                skuRechargeEntity.setSku(9011L);
                skuRechargeEntity.setOutBusinessNo(RandomStringUtils.randomNumeric(12));
                String orderId = raffleOrder.createSkuRechargeOrder(skuRechargeEntity);
                log.info("测试结果:{}", orderId);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        new CountDownLatch(1).await();
    }


}
