package com.bots.vkbot.service;

import com.bots.vkbot.utils.VkApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MessageService {

    public void handleEvent(Map<String, Object> event) {
        log.info("Received event: " + event);

        @SuppressWarnings("unchecked")
        Map<String, Object> message = (Map<String, Object>)event.get("object");
        String text = (String) message.get("text");
        Integer userId = (Integer) message.get("from_id");
        String responseMsg = "Вы сказали: " + text;
        VkApiUtils.sendMessage(userId, responseMsg);
        log.info("Response - " + responseMsg);
        log.info("User - " + userId);
    }
}
