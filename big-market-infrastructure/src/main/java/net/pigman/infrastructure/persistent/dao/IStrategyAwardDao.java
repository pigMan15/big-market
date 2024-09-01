package net.pigman.infrastructure.persistent.dao;

import net.pigman.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName net.pigman.infrastructure.persistent.dao
 *
 * @author pig泉
 * @version 1.0.0
 * @interface IStrategyAwardDao
 * @date 2024/9/1
 * @description
 */
@Mapper
public interface IStrategyAwardDao {

    List<StrategyAward> queryStrategyAwardList();

    List<StrategyAward> queryStrategyAwardListByStrategyId(Long strategyId);

}
