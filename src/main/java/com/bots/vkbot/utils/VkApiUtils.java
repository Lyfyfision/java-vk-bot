package com.bots.vkbot.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

@Slf4j
public class VkApiUtils {
    public static final String API_URL = "https://api.vk.com/method/";
    public static final String API_VERSION = "5.81";
    public static final String ACCESS_TOKEN = "vk1.a.LUq8YGnjeb6s2FBbuh7stWkLihf2nry0ZmGCQFLEFN-xF1Cxo2Xyx__BiTnGTRD3semCxn0T_2bT8EaAMUFjJ_iRI7lkFyCgZQF8jiTikSX0JC09f0V-aRogmPeJqoBnbfL1wxGDRUrNnvM8JlEWLa7qIBNAiBWZc3bM4Hh71yExUzMxXrEMpcZOSmlEZJSm5BLVIIFCtqtByRFc1ytB8Q";
    public static final String CONFIRMATION_TOKEN = "4c8e68a2";

    public static void sendMessage(Integer userId, String msg) {
        Random random = new Random();
        int randomId = random.nextInt();
        try {
            String urlString = String.format(
                    "%smessages.send?peer_id=%d&message=%s&access_token=%s&v=%s&random_id=%d",
                    API_URL, userId, URLEncoder.encode(msg, "UTF-8"), ACCESS_TOKEN, API_VERSION, randomId
            );
            HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            log.info("Response code - " + responseCode);

            try (InputStream in = (responseCode == 200) ? conn.getInputStream() : conn.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                log.info("Response body: " + response);
            }

        } catch (MalformedURLException e) {
            log.error("Некорректный URL: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Ошибка при соединении: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private VkApiUtils() {
        throw new UnsupportedOperationException("Do not init a utility class");
    }
}
