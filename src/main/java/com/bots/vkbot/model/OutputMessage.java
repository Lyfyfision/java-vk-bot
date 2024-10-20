package com.bots.vkbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OutputMessage {
    private final Integer userId;
    private final String text;
}
