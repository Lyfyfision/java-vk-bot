package com.bots.vkbot.service;

import com.bots.vkbot.utils.VkApiConstants;
import com.bots.vkbot.utils.connection.ConnectionFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    private final ConnectionFactory connectionFactory;

    public void handleEvent(Map<String, Object> event) {
        log.info("Received event: " + event);

        @SuppressWarnings("unchecked")
        Map<String, Object> message = (Map<String, Object>)event.get("object");
        String text = (String) message.get("text");
        Integer userId = (Integer) message.get("from_id");
        String responseMsg = "Вы сказали: " + text;

        try {
            sendMessage(userId, responseMsg);
            log.info("Response - " + responseMsg);
            log.info("User - " + userId);
        } catch (IOException e) {
            log.error("Error sending message", e);
        }
    }

    private void sendMessage(int userId, String message) throws IOException {
        String encodedMessage = java.net.URLEncoder.encode(message, StandardCharsets.UTF_8);
        String urlString = String.format(
                VkApiConstants.API_URL + "messages.send?peer_id=%d&message=%s&access_token=%s&v=%s",
                userId, encodedMessage, VkApiConstants.ACCESS_TOKEN, VkApiConstants.API_VERSION
        );
        HttpURLConnection connection = connectionFactory.createConnection(urlString);
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Error sending message: HTTP response code " + responseCode);
        }
    }
}
