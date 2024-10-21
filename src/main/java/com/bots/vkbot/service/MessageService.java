package com.bots.vkbot.service;

import com.bots.vkbot.model.Event;
import com.bots.vkbot.model.InputMessage;
import com.bots.vkbot.model.OutputMessage;
import com.bots.vkbot.utils.SendMsgUrlBuilder;
import com.bots.vkbot.utils.VkApiConstants;
import com.bots.vkbot.utils.connection.ConnectionFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    private final ConnectionFactory connectionFactory;

    public void handleEvent(Event event) {
        log.info("Received event: " + event.getData());

        if ("message_new".equals(event.getType())) {
            Map<String, Object> message = event.getData();
            InputMessage inputMessage = new InputMessage(
                    (Integer) message.get("from_id"),
                    (String) message.get("text")
            );
            String responseMsg = "Вы сказали: " + inputMessage.getText();
            OutputMessage outputMessage = new OutputMessage(inputMessage.getUserId(), responseMsg);

            try {
                sendMessage(outputMessage);
                log.info("Response - " + outputMessage.getText());
                log.info("User - " + outputMessage.getUserId());
            } catch (IOException e) {
                log.error("Error sending message", e);
            }
        }
    }

    private void sendMessage(OutputMessage message) throws IOException {
        String url = new SendMsgUrlBuilder(VkApiConstants.API_URL)
                .peerId(message.getUserId())
                .message(message.getText())
                .accessToken(VkApiConstants.ACCESS_TOKEN)
                .apiVersion(VkApiConstants.API_VERSION)
                .build();
        HttpURLConnection connection = connectionFactory.createConnection(url);
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Error sending message: HTTP response code " + responseCode);
        }
    }
}
