package com.bots.vkbot.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SendMsgUrlBuilder {
    private final String baseUrl;
    private long peerId;
    private String message;
    private String accessToken;
    private String apiVersion;

    public SendMsgUrlBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public SendMsgUrlBuilder peerId(long peerId) {
        this.peerId = peerId;
        return this;
    }

    public SendMsgUrlBuilder message(String message) {
        this.message = URLEncoder.encode(message, StandardCharsets.UTF_8);
        return this;
    }

    public SendMsgUrlBuilder accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public SendMsgUrlBuilder apiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public String build() {
        return String.format(
                "%smessages.send?peer_id=%d&message=%s&access_token=%s&v=%s",
                baseUrl, peerId, message, accessToken, apiVersion
        );
    }
}
