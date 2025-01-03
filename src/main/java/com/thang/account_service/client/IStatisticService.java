package com.thang.account_service.client;

import com.thang.account_service.model.StatisticDTO;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.security.OAuth2AccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@SuppressWarnings("deprecation")
@FeignClient(name = "statistic-service", url = "http://localhost:9082", fallback = StatisticServiceImpl.class
,configuration = StatisticFeignClientConfiguration.class)
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

@SuppressWarnings("deprecation")
class StatisticFeignClientConfiguration {

    private final String CLIENT_REGISTRATION_ID = "a-service";

    @Bean
    RequestInterceptor requestInterceptor(OAuth2AuthorizedClientManager authorizedClientManager) {
        return new OAuth2AccessTokenInterceptor(CLIENT_REGISTRATION_ID, authorizedClientManager);
    }
}
