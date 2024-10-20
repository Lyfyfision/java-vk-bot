package com.bots.vkbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
@AllArgsConstructor
@Getter
public class Event {
    private final String type;
    private final Map<String, Object> data;
}
