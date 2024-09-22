package net.pigman.api;

import net.pigman.api.dto.RaffleAwardListRequestDTO;
import net.pigman.api.dto.RaffleAwardListResponseDTO;
import net.pigman.api.dto.RaffleRequestDTO;
import net.pigman.api.dto.RaffleResponseDTO;
import net.pigman.types.model.Response;

import java.util.List;

/**
 * packageName net.pigman.api
 *
 * @author pig泉
 * @version 1.0.0
 * @className IRaffleService
 * @date 2024/9/22
 * @description 抽奖服务接口
 */
public interface IRaffleService {

    /**
     * @description 策略装配接口
     * @param strategyId:
     * return Response<Boolean>
     * @author pig泉
     * @date 14:12 2024/9/22
     * {@link Response<Boolean>}
     */
    Response<Boolean> strategyArmory(Long strategyId);

    /**
     * @description 抽奖奖品列表查询接口
     * @param requestDTO:
     * return Response<List<RaffleAwardListResponseDTO>>
     * @author pig泉
     * @date 14:13 2024/9/22
     * {@link Response<List<RaffleAwardListResponseDTO>>}
     */
    Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(RaffleAwardListRequestDTO requestDTO);

    /**
     * @description 随机抽奖接口
     * @param raffleRequestDTO:
     * return Response<RaffleResponseDTO>
     * @author pig泉
     * @date 14:14 2024/9/22
     * {@link Response<RaffleResponseDTO>}
     */
    Response<RaffleResponseDTO> randomRaffle(RaffleRequestDTO raffleRequestDTO);

}
