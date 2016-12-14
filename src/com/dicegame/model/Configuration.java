package com.dicegame.model;

import java.util.List;

/**
 * Created by Karol on 13/12/2016.
 */
public class Configuration {

    private int humansNum;
    private int botsNum;
    private String gameName;
    private GameType gameType;
    private int numberOfPointsToWin;
    private List<Bot> listOfBots;

    public Configuration(int humansNum, int botsNum, String gameName, GameType gameType, int numberOfPointsToWin, List<Bot> listOfBots) {
        this.humansNum = humansNum;
        this.botsNum = botsNum;
        this.gameName = gameName;
        this.gameType = gameType;
        this.numberOfPointsToWin = numberOfPointsToWin;
        this.listOfBots = listOfBots;
    }
}
