package net.pigman.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName net.pigman.api.dto
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleAwardListResponseDTO
 * @date 2024/9/22
 * @description 抽奖奖品列表响应DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardListResponseDTO {

    // 奖品id
    private Integer awardId;

    // 奖品标题
    private String awardTitle;

    // 奖品副标题
    private String awardSubtitle;

    // 奖品排序
    private Integer sort;

}
