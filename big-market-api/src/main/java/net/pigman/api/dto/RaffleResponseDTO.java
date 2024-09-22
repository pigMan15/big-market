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
 * @className RaffleResponseDTO
 * @date 2024/9/22
 * @description 抽奖响应参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleResponseDTO {

    // 奖品id
    private Integer awardId;

    // 奖品排序编号
    private Integer awardIndex;

}
