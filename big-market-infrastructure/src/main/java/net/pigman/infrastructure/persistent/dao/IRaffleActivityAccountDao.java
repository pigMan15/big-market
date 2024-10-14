package net.pigman.infrastructure.persistent.dao;

import net.pigman.infrastructure.persistent.po.RaffleActivityAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName net.pigman.infrastructure.persistent.dao
 *
 * @author pig泉
 * @version 1.0.0
 * @className IRaffleActivityAccountDao
 * @date 2024/10/4
 * @description 抽奖活动账户DAO
 */
@Mapper
public interface IRaffleActivityAccountDao {

    void insert(RaffleActivityAccount raffleActivityAccount);

    Integer updateAccountQuota(RaffleActivityAccount raffleActivityAccount);

}
