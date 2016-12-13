package com.dicegame.model;

import java.util.List;

/**
 * Created by Karol on 13/12/2016.
 */
public class GameState {

    private List<Player> listOfPlayers;
    private String status;
    private String activePlayer;

    public GameState(List<Player> listOfPlayers, String status) {
        this.listOfPlayers = listOfPlayers;
        this.status = status;
    }
}
