package net.pigman.infrastructure.persistent.dao;

import net.pigman.infrastructure.persistent.po.RaffleActivitySku;
import org.apache.ibatis.annotations.Mapper;

/**
 * packageName net.pigman.infrastructure.persistent.dao
 *
 * @author pig泉
 * @version 1.0.0
 * @className IRaffleActivityAccountFlowDao
 * @date 2024/10/4
 * @description 商品SkuDAO
 */
@Mapper
public interface IRaffleActivitySkuDao {

    RaffleActivitySku queryActivitySku(Long sku);

}
