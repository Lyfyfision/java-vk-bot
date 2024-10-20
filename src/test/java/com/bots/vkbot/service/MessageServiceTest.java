package com.bots.vkbot.service;

import com.bots.vkbot.utils.VkApiConstants;
import com.bots.vkbot.utils.connection.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    private MessageService messageService;
    private ConnectionFactory mockConnectionFactory;
    private HttpURLConnection mockConnection;
    private Map<String, Object> event;
    private Map<String, Object> message;

    @BeforeEach
    public void setUp() {
        mockConnectionFactory = mock(ConnectionFactory.class);
        mockConnection = mock(HttpURLConnection.class);
        messageService = new MessageService(mockConnectionFactory);
        event = new HashMap<>();
        message = new HashMap<>();
        message.put("text", "Hello, world!");
        message.put("from_id", 123456);
        event.put("object", message);
    }

    @Test
    @DisplayName("Request URL can be properly created")
    void testHandleEvent_shouldGeneratesCorrectRequestUrl() throws IOException {
        // Arrange
        String expectedMessage = "Вы сказали: Hello, world!";
        int expectedUserID = 123456;
        String expectedEncodedMessage = java.net.URLEncoder.encode(expectedMessage, StandardCharsets.UTF_8);
        String expectedURL = String.format(
                VkApiConstants.API_URL + "messages.send?peer_id=%d&message=%s&access_token=%s&v=%s",
                expectedUserID, expectedEncodedMessage, VkApiConstants.ACCESS_TOKEN, VkApiConstants.API_VERSION
        );

        when(mockConnectionFactory.createConnection(anyString())).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(200);

        ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);

        // Act
        messageService.handleEvent(event);

        // Assert
        verify(mockConnectionFactory).createConnection(urlCaptor.capture());
        String capturedURL = urlCaptor.getValue();
        assertEquals(capturedURL, expectedURL, "Generated URL does not match the expected pattern.");
    }
}
