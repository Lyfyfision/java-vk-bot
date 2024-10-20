package com.bots.vkbot.utils.connection;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ConnectionFactoryImpl implements ConnectionFactory {
    @Override
    public HttpURLConnection createConnection(String url) throws IOException {
        URL urlObj = new URL(url);
        return (HttpURLConnection) urlObj.openConnection();
    }
}
