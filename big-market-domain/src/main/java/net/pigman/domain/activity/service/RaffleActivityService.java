package net.pigman.domain.activity.service;

import net.pigman.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * packageName net.pigman.domain.activity.service
 *
 * @author pig泉
 * @version 1.0.0
 * @className RaffleActivityService
 * @date 2024/10/7
 * @description 抽奖活动服务
 */
@Service
public class RaffleActivityService extends AbstractRaffleActivity {


    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }
}
