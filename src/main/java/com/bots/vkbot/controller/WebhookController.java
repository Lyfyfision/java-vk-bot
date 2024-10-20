package com.bots.vkbot.controller;

import com.bots.vkbot.service.MessageService;
import com.bots.vkbot.utils.VkApiConstants;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/webhook")
public class WebhookController {
    private final MessageService messageService;

    @PostMapping
    public String receiveEvent(@RequestBody Map<String, Object> event) {
        String eventType = (String) event.get("type");

        if ("confirmation".equals(eventType)) {
            return VkApiConstants.CONFIRMATION_TOKEN;
        } else if("message_new".equals(eventType)) {
            messageService.handleEvent(event);
        }
        return "ok";
    }
}
