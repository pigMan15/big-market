package net.pigman.infrastructure.persistent.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import net.pigman.infrastructure.persistent.po.RaffleActivityOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * packageName net.pigman.infrastructure.persistent.dao
 *
 * @author pig泉
 * @version 1.0.0
 * @className IRaffleActivityOrderDao
 * @date 2024/10/4
 * @description 抽奖活动订单DAO
 */
@Mapper
@DBRouterStrategy(splitTable = true)
public interface IRaffleActivityOrderDao {

    @DBRouter(key = "userId")
    void insert(RaffleActivityOrder raffleActivityOrder);

    @DBRouter
    List<RaffleActivityOrder> queryRaffleActivityOrderByUserId(String userId);

}
