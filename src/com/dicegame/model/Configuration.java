package com.dicegame.model;

import java.util.List;

/**
 * Created by Karol on 13/12/2016.
 */
public class Configuration {

    private int humansNum;
    private GameType gameType;
    private int numberOfPointsToWin;
    private List<BotConfiguration> listOfBotConfigurations;

    public Configuration(int humansNum, GameType gameType, int numberOfPointsToWin, List<BotConfiguration> listOfBotConfigurations) {
        this.humansNum = humansNum;
        this.gameType = gameType;
        this.numberOfPointsToWin = numberOfPointsToWin;
        this.listOfBotConfigurations = listOfBotConfigurations;
    }
}
