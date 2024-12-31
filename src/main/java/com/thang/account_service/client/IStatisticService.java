package com.thang.account_service.client;

import com.thang.account_service.model.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", url = "http://localhost:9082", fallback = StatisticServiceImpl.class)
public interface IStatisticService {
    @PostMapping("/createNewStatistic.do")
    void createNewStatistic(@RequestBody StatisticDTO statisticDTO);
}
@Component
class StatisticServiceImpl implements IStatisticService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void createNewStatistic(StatisticDTO statisticDTO) {
        //fallback
        logger.error("Statistic service is slow");
    }
}
