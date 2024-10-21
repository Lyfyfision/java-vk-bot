package com.bots.vkbot.utils;

/**
 * A utility class that stores important constants. Also, there is an option to retrieve tokens from files by {@link Tokens},
 * but {@link #ACCESS_TOKEN_FILE} and {@link #CONFIRMATION_TOKEN_FILE} needs to be specified.
 *
 *
 * <p><b>NOTE:</b> Fields like {@link #ACCESS_TOKEN} and {@link #CONFIRMATION_TOKEN} are mandatory for bot working.
 */
public class VkApiConstants {
    public static final String API_URL = "https://api.vk.com/method/";
    public static final String API_VERSION = "5.81";
    //Insert here your access token from community setting
    public static final String ACCESS_TOKEN = " ";
    //Insert here your confirmation token from community setting
    public static final String CONFIRMATION_TOKEN = " ";
    public static final String ACCESS_TOKEN_FILE = " ";
    public static final String CONFIRMATION_TOKEN_FILE = " ";
    private VkApiConstants() {
        throw new UnsupportedOperationException("Do not init a utility class");
    }
}
