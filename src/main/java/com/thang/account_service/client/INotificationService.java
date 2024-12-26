package com.thang.account_service.client;

import com.thang.account_service.model.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "notification-service", url = "http://localhost:9084")
public interface INotificationService {
    @PostMapping("/sendNotificationEmail.do")
    void sendNotificationEmail(@RequestBody MessageDTO messageDTO);
}
