package net.pigman.trigger.http;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.pigman.api.IRaffleService;
import net.pigman.api.dto.RaffleAwardListRequestDTO;
import net.pigman.api.dto.RaffleAwardListResponseDTO;
import net.pigman.api.dto.RaffleRequestDTO;
import net.pigman.api.dto.RaffleResponseDTO;
import net.pigman.domain.strategy.model.entity.RaffleAwardEntity;
import net.pigman.domain.strategy.model.entity.RaffleFactorEntity;
import net.pigman.domain.strategy.model.entity.StrategyAwardEntity;
import net.pigman.domain.strategy.service.IRaffleAward;
import net.pigman.domain.strategy.service.IRaffleStrategy;
import net.pigman.domain.strategy.service.armory.IStrategyArmory;
import net.pigman.types.enums.ResponseCode;
import net.pigman.types.exception.AppException;
import net.pigman.types.model.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName net.pigman.trigger.http
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleController
 * @date 2024/9/22
 * @description 营销抽奖服务
 */
@Slf4j
@RestController
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/raffle/")
public class RaffleController implements IRaffleService {

    @Resource
    private IStrategyArmory strategyArmory;

    @Resource
    private IRaffleAward raffleAward;

    @Resource
    private IRaffleStrategy raffleStrategy;

    @RequestMapping(value = "strategyArmory", method = RequestMethod.GET)
    @Override
    public Response<Boolean> strategyArmory(@RequestParam Long strategyId) {
        try {
            log.info("抽奖策略装配开始 strategyId:{}", strategyId);
            boolean result = strategyArmory.assembleLotteryStrategy(strategyId);
            Response<Boolean> response = Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(result)
                    .build();
            log.info("抽奖策略装配完成 strategyId:{}, response:{}", strategyId, JSON.toJSONString(response));
            return response;
        } catch (Exception e) {
            log.info("抽奖策略装配失败 strategyId:{}, 异常原因:{}", strategyId, e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @RequestMapping(value = "queryRaffleAwardList", method = RequestMethod.POST)
    @Override
    public Response<List<RaffleAwardListResponseDTO>> queryRaffleAwardList(@RequestBody RaffleAwardListRequestDTO requestDTO) {
        try {
            log.info("查询抽奖奖品列表开始, strategyId:{}", requestDTO.getStrategyId());
            List<StrategyAwardEntity> strategyAwardEntityList = raffleAward.queryRaffleStrategyAwardList(requestDTO.getStrategyId());
            List<RaffleAwardListResponseDTO> raffleAwardListResponseDTOS = new ArrayList<>(strategyAwardEntityList.size());
            for (StrategyAwardEntity strategyAward: strategyAwardEntityList) {
                raffleAwardListResponseDTOS.add(
                        RaffleAwardListResponseDTO.builder()
                                .awardId(strategyAward.getAwardId())
                                .awardTitle(strategyAward.getAwardTitle())
                                .awardSubtitle(strategyAward.getAwardSubtitle())
                                .sort(strategyAward.getSort())
                                .build()
                );
            }
            Response<List<RaffleAwardListResponseDTO>> response = Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(raffleAwardListResponseDTOS)
                    .build();
            log.info("查询抽奖奖品列表完成, strategyId:{}, response:{}", requestDTO.getStrategyId(), JSON.toJSONString(response));
            return response;
        } catch (Exception e) {
            log.error("查询抽奖奖品列表失败, strategyId:{}, 异常信息:{}", requestDTO.getStrategyId(), e);
            return Response.<List<RaffleAwardListResponseDTO>>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

    @RequestMapping(value = "randomRaffle", method = RequestMethod.POST)
    @Override
    public Response<RaffleResponseDTO> randomRaffle(@RequestBody RaffleRequestDTO raffleRequestDTO) {
        try {
            log.info("随机抽奖开始, strategyId:{}", raffleRequestDTO.getStrategyId());
            RaffleAwardEntity raffleAwardEntity = raffleStrategy.performRaffle(RaffleFactorEntity.builder()
                    .userId("sysetm")
                    .strategyId(raffleRequestDTO.getStrategyId())
                    .build());
            Response<RaffleResponseDTO> response = Response.<RaffleResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(RaffleResponseDTO.builder()
                            .awardId(raffleAwardEntity.getAwardId())
                            .awardIndex(raffleAwardEntity.getSort())
                            .build()
                    ).build();
            log.info("随机抽奖完成, strategyId:{}, response:{}", raffleRequestDTO.getStrategyId(), JSON.toJSONString(response));
            return response;
        } catch (AppException e) {
            log.info("随机抽奖失败, strategyId:{}, 异常原因:{}", raffleRequestDTO.getStrategyId(), e.getInfo());
            return Response.<RaffleResponseDTO>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        } catch (Exception e) {
            log.info("随机抽奖失败, strategyId:{}, 异常原因:{}", raffleRequestDTO.getStrategyId(), e);
            return Response.<RaffleResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }
}
