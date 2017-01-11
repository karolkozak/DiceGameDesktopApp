package com.dicegame.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Karol on 13/12/2016.
 */
public enum BotLevel {
    EASY("Latwy"), MASTER("Mistrz");
    private String value;

    private BotLevel(String value) {
        this.value = value;
    }

    // Reverse-lookup map for getting a BotLevel from a value
    private static final Map<String, BotLevel> lookup = new HashMap<String, BotLevel>();

    static {
        for (BotLevel botLevel : BotLevel.values()) {
            lookup.put(botLevel.getValue(), botLevel);
        }
    }

    public String getValue() {
        return value;
    }

    public static BotLevel get(String value) {
        return lookup.get(value);
    }
}
