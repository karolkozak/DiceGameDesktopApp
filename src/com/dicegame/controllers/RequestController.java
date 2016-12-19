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
    public void login(String nick) {

    }

    @Override
    public List<Integer> rollDice(Move move) {
        return null;
    }

    @Override
    public void joinGame(Game game) {

    }

    @Override
    public void spectateGame(Game game) {

    }

    @Override
    public void exitGame() {

    }

    @Override
    public void createGame(Configuration config) {

    }


}
