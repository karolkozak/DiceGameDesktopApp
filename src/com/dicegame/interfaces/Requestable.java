package com.dicegame.interfaces;

import com.dicegame.model.Configuration;
import com.dicegame.model.Game;
import com.dicegame.model.Move;

import java.util.List;

/**
 * Created by Jakub on 2016-12-18.
 */
public interface Requestable {

    List<Game> getAvailableGames();
    void login(String nick);
    List<Integer> rollDice(Move move);
    void joinGame(Game game);
    void spectateGame(Game game);
    void exitGame();
    void createGame(Configuration config);



}
