package net.pigman.test.infrastructure;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.infrastructure.persistent.dao.IRaffleActivityDao;
import net.pigman.infrastructure.persistent.po.RaffleActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * packageName net.pigman.test.infrastructure
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivityDaoTest
 * @date 2024/10/4
 * @description TODO
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RaffleActivityDaoTest {

    @Resource
    private IRaffleActivityDao raffleActivityDao;

    @Test
    public void testQueryRaffleActivityByActivityId() {
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(100301L);
        log.info("测试结果：{}", JSON.toJSONString(raffleActivity));
    }

}
