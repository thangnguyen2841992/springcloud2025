package com.thang.account_service.client;

import com.thang.account_service.model.StatisticDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", url = "http://localhost:9082")
public interface IStatisticService {
    @PostMapping("/createNewStatistic.do")
    void createNewStatistic(@RequestBody StatisticDTO statisticDTO);
}
