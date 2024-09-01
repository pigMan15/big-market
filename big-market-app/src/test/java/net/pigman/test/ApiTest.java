package net.pigman.test;

import lombok.extern.slf4j.Slf4j;
import net.pigman.infrastructure.persistent.redis.IRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IRedisService redisService;

    @Test
    public void test() {
        Map<String, Object> map = redisService.getMap("strategy_id_100001");
        map.put("1", 101);
        map.put("2", 102);
        map.put("3", 103);
        log.info("测试结果：{}", redisService.getFromMap("strategy_id_100001", "1"));
    }

}
