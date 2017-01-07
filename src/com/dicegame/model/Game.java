package com.dicegame.model;

/**
 * Created by Karol on 13/12/2016.
 */
public class Game {

    private int gameID;
    private GameType gameType;
    private int placesLeft;
    private GameState gameState;

    public Game(GameType gameType, int placesLeft) {
        this.gameType = gameType;
        this.placesLeft = placesLeft;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameType getGameType() {
        return gameType;
    }

    public int getPlacesLeft() {
        return placesLeft;
    }

    public GameState getGameState() {
        return gameState;
    }
}
