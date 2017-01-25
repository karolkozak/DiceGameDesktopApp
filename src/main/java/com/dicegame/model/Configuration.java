package com.dicegame.model;

import java.util.List;

/**
 * Created by Karol on 13/12/2016.
 */
public class Configuration {

    private Integer humansNum;
    private GameType gameType;
    private Integer numberOfPointsToWin;
    private List<BotConfiguration> listOfBots;

    public Configuration(int humansNum, GameType gameType, int numberOfPointsToWin, List<BotConfiguration> listOfBots) {
        this.humansNum = humansNum;
        this.gameType = gameType;
        this.numberOfPointsToWin = numberOfPointsToWin;
        this.listOfBots = listOfBots;
    }
}
