package com.bots.vkbot.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utility class with one static method to retrieve any tokens from file on local machine. Was created to replace
 * using constants from {@link VkApiConstants} directly in code.
 */
@Slf4j
public class Tokens {

    public static String getToken(String filePath) throws IOException {
        String token;
        try {
            token = Files.readString(Path.of(filePath));
        } catch (IOException e) {
            log.error("Error occurred during file reading: " + filePath);
            throw e;
        }
        return token;
    }

    private Tokens() {
        throw new UnsupportedOperationException("Do not init a utility class");
    }
}
