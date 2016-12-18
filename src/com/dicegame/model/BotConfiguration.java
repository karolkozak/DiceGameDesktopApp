package com.dicegame.model;

/**
 * Created by Karol on 13/12/2016.
 */
public class BotConfiguration {

    private String name;
    private BotLevel botLevel;

    public BotConfiguration(String name, BotLevel botLevel) {
        this.name = name;
        this.botLevel = botLevel;
    }
}
