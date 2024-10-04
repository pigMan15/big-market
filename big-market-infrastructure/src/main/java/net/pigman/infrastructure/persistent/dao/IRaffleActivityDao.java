package net.pigman.infrastructure.persistent.dao;

import net.pigman.infrastructure.persistent.po.RaffleActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName net.pigman.infrastructure.persistent.dao
 *
 * @author pig泉
 * @version 1.0.0
 * @className IRaffleActivityDao
 * @date 2024/10/4
 * @description 抽奖活动DAO
 */
@Mapper
public interface IRaffleActivityDao {

    RaffleActivity queryRaffleActivityByActivityId(Long activityId);

}
