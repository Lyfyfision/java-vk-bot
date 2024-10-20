package com.bots.vkbot.utils.connection;

import java.io.IOException;
import java.net.HttpURLConnection;

public interface ConnectionFactory {
    HttpURLConnection createConnection(String url) throws IOException;
}
