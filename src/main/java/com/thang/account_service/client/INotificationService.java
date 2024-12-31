package com.thang.account_service.client;

import com.thang.account_service.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "notification-service", url = "http://localhost:9084", fallback = NotificationServiceImpl.class)
public interface INotificationService {
    @PostMapping("/sendNotificationEmail.do")
    void sendNotificationEmail(@RequestBody MessageDTO messageDTO);
}

@Component
class NotificationServiceImpl implements INotificationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void sendNotificationEmail(MessageDTO messageDTO) {
        logger.error("Notification service is slow");
    }
}
