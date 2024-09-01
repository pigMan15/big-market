package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className StrategyAward
 * @date 2024/9/1
 * @description 抽奖策略明细配置
 */
@Data
public class StrategyAward {

    private Long id;

    private Long strategyId;

    private Integer awardId;

    private String awardTitle;

    private String awardSubtitle;

    private Integer awardCount;

    private Integer awardCountSurplus;

    private BigDecimal awardRate;

    private String ruleModels;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

}
