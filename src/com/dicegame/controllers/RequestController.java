package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import com.dicegame.model.Configuration;
import com.dicegame.model.Game;
import com.dicegame.model.Move;

import java.util.List;

/**
 * Created by Jakub on 2016-12-18.
 */
public class RequestController implements Requestable {
    @Override
    public List<Game> getAvailableGames() {
        return null;
    }

    @Override
    public boolean login(String nick) {
        return false;
    }

    @Override
    public List<Integer> rollDice(Move move) {
        return null;
    }

    @Override
    public boolean joinGame(Game game) {
        return false;
    }

    @Override
    public boolean spectateGame(Game game) {
        return false;
    }

    @Override
    public boolean exitGame() {
        return false;
    }

    @Override
    public boolean createGame(Configuration config) {
        return false;
    }//JMS SHIT


}
