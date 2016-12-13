package com.dicegame.model;

/**
 * Created by Karol on 13/12/2016.
 */
public class Game {

    private int gameID;
    private String gameName;
    private GameType gameType;
    private int leftPlaces;

    public Game(String gameName, GameType gameType, int leftPlaces) {
        this.gameName = gameName;
        this.gameType = gameType;
        this.leftPlaces = leftPlaces;
    }

    //get from logic
}
