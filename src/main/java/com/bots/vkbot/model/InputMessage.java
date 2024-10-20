package com.bots.vkbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InputMessage {
    private final Integer userId;
    private final String text;
}
