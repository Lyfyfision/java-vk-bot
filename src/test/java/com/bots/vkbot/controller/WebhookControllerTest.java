package com.bots.vkbot.controller;

import com.bots.vkbot.service.MessageService;
import com.bots.vkbot.utils.VkApiConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebhookController.class)
class WebhookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageService messageService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, Object> event;

    @BeforeEach
    public void setUp() {
        event = new HashMap<>();
    }

    @Test
    @DisplayName("Token can be conformed")
    void testReceiveEvent_whenPostConfirmationRequest_shouldReturnConfirmationToken() throws Exception {
        // Arrange
        event.put("type", "confirmation");

        // Act & Assert
        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(content().string(VkApiConstants.CONFIRMATION_TOKEN));
    }

    @Test
    @DisplayName("New message can be processed")
    void testReceiveEvent_whenGiveNewMessageEvent_shouldReturnOk() throws Exception {
        // Arrange
        event.put("type", "message_new");
        Map<String, Object> objectData = new HashMap<>();
        objectData.put("text", "Hello, world!");
        objectData.put("from_id", 123456);
        event.put("object", objectData);

        // Act & Assert
        mockMvc.perform(post("/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));

        verify(messageService).handleEvent(event);
    }
}
