package net.pigman.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * packageName net.pigman.infrastructure.persistent.po
 *
 * @author pig泉
 * @version 1.0.0
 * @className Award
 * @date 2024/9/1
 * @description 奖品
 */
@Data
public class Award {

    private Long id;

    private Integer awardId;

    private String awardKey;

    private String awardConfig;

    private String awardDesc;

    private Date createTime;

    private Date updateTime;

}
