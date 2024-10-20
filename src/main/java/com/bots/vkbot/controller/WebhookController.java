package com.bots.vkbot.controller;

import com.bots.vkbot.model.Event;
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
    public String receiveEvent(@RequestBody Map<String, Object> eventMap) {
        @SuppressWarnings("unchecked")
        Event event = new Event((String) eventMap.get("type"), (Map<String, Object>) eventMap.get("object"));

        if ("confirmation".equals(event.getType())) {
            return VkApiConstants.CONFIRMATION_TOKEN;
        } else {
            messageService.handleEvent(event);
        }
        return "ok";
    }
}
