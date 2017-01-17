package com.dicegame.model;

import java.util.List;

/**
 * Created by Karol on 13/12/2016.
 */
public class GameState {

    private List<Player> listOfPlayers;
    private GameStatus status;
    private String activePlayer;
    private int winningNumber;
    private int numberOfPointsToWin;

    public GameState(List<Player> listOfPlayers, GameStatus status, String activePlayer, int winningNumber, int numberOfPointsToWin) {
        this.listOfPlayers = listOfPlayers;
        this.status = status;
        this.activePlayer = activePlayer;
        this.winningNumber = winningNumber;
        this.numberOfPointsToWin = numberOfPointsToWin;
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

}
