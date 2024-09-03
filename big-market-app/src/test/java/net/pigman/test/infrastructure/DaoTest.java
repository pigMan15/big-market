package net.pigman.test.infrastructure;

import lombok.extern.slf4j.Slf4j;
import net.pigman.infrastructure.persistent.dao.IAwardDao;
import net.pigman.infrastructure.persistent.dao.IStrategyDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * packageName net.pigman.test.infrastructure
 *
 * @author pig泉
 * @version 1.0.0
 * @className DaoTest
 * @date 2024/9/1
 * @description
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Autowired
    IAwardDao iAwardDao;

    @Autowired
    IStrategyDao strategyDao;

    @Test
    public void awardTest() {
        log.info("测试结果:{}", iAwardDao.queryAwardList());
        log.info("测试结果:{}", strategyDao.queryStrategyByStrategyId(100001l));
    }

}
