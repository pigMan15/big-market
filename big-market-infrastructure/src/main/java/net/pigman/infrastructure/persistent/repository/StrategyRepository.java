package net.pigman.infrastructure.persistent.repository;

import net.pigman.domain.strategy.model.entity.StrategyAwardEntity;
import net.pigman.domain.strategy.model.entity.StrategyEntity;
import net.pigman.domain.strategy.model.entity.StrategyRuleEntity;
import net.pigman.domain.strategy.repository.IStrategyRepository;
import net.pigman.infrastructure.persistent.dao.IStrategyAwardDao;
import net.pigman.infrastructure.persistent.dao.IStrategyDao;
import net.pigman.infrastructure.persistent.dao.IStrategyRuleDao;
import net.pigman.infrastructure.persistent.po.Strategy;
import net.pigman.infrastructure.persistent.po.StrategyAward;
import net.pigman.infrastructure.persistent.po.StrategyRule;
import net.pigman.infrastructure.persistent.redis.IRedisService;
import net.pigman.types.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * packageName net.pigman.infrastructure.persistent.repository
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyRepository
 * @date 2024/9/1
 * @description 策略服务仓储实现
 */
@Repository
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyAwardDao strategyAwardDao;

    @Resource
    private IRedisService redisService;

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyRuleDao strategyRuleDao;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId) {
        // 从缓存中获取
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntityList = redisService.getValue(cacheKey);
        if (!CollectionUtils.isEmpty(strategyAwardEntityList)) {
            return  strategyAwardEntityList;
        }

        // 查库
        List<StrategyAward> strategyAwardList = strategyAwardDao.queryStrategyAwardListByStrategyId(strategyId);
        strategyAwardEntityList = strategyAwardList.stream().map(item -> {
            StrategyAwardEntity strategyAwardEntity = new StrategyAwardEntity();
            strategyAwardEntity.setStrategyId(item.getStrategyId());
            strategyAwardEntity.setAwardId(item.getAwardId());
            strategyAwardEntity.setAwardCount(item.getAwardCount());
            strategyAwardEntity.setAwardCountSurplus(item.getAwardCountSurplus());
            strategyAwardEntity.setAwardRate(item.getAwardRate());
            return strategyAwardEntity;
        }).collect(Collectors.toList());
        redisService.setValue(cacheKey, strategyAwardList);
        return strategyAwardEntityList;
    }

    @Override
    public void storeStrategyAwardSearchRateTable(String strategyId, Integer rateRange, Map<String, Integer> strategyAwardSearchRateTable) {
        // 存储抽奖策略范围值，用于生成范围值
        redisService.setValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId, rateRange);

        // 存储概率查找表
        Map<String, Integer> cacheRectTable = redisService.getMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId);
        cacheRectTable.putAll(strategyAwardSearchRateTable);

    }

    @Override
    public Integer getStrategyAwardAssemble(String key, Integer rateKey) {
        String value = redisService.getFromMap(Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + key, String.valueOf(rateKey));
        return StringUtils.isNotBlank(value) ? Integer.valueOf(value) : 0;
    }

    @Override
    public int getRateRange(Long strategyId) {
        return getRateRange(String.valueOf(strategyId));
    }

    @Override
    public int getRateRange(String key) {
        return redisService.getValue(Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + key);
    }

    @Override
    public StrategyEntity queryStrategyEntityByStrategyId(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = redisService.getValue(cacheKey);
        if (Objects.nonNull(strategyEntity)) {
            return strategyEntity;
        }
        Strategy strategy = strategyDao.queryStrategyByStrategyId(strategyId);
        strategyEntity = StrategyEntity.builder()
                .strategyId(strategyId)
                .strategyDesc(strategy.getStrategyDesc())
                .ruleModels(strategy.getRuleModels())
                .build();
        redisService.setValue(cacheKey, strategyEntity);
        return strategyEntity;
    }

    @Override
    public StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel) {
        StrategyRule param = new StrategyRule();
        param.setStrategyId(strategyId);
        param.setRuleModel(ruleModel);
        StrategyRule strategyRule = strategyRuleDao.queryStrategyRule(param);
        return StrategyRuleEntity.builder()
                .strategyId(strategyId)
                .awardId(strategyRule.getAwardId())
                .ruleType(strategyRule.getRuleType())
                .ruleModel(strategyRule.getRuleModel())
                .ruleValue(strategyRule.getRuleValue())
                .ruleDesc(strategyRule.getRuleDesc())
                .build();
    }
}
