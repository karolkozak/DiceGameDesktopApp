package com.dicegame.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Karol on 13/12/2016.
 */
public enum GameType {
    POKER("Poker"), N_PLUS("N+"), N_STAR("N*");
    private String value;

    private GameType(String value) {
        this.value = value;
    }

    // Reverse-lookup map for getting a GameType from a value
    private static final Map<String, GameType> lookup = new HashMap<String, GameType>();

    static {
        for (GameType gameType : GameType.values()) {
            lookup.put(gameType.getValue(), gameType);
        }
    }

    public String getValue() {
        return value;
    }

    public static GameType get(String value) {
        return lookup.get(value);
    }
}