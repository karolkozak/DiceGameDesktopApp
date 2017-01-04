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
    boolean login(String nick);
    List<Integer> rollDice(Move move);
    boolean joinGame(Game game);
    boolean spectateGame(Game game);
    boolean exitGame();
    boolean createGame(Configuration config);


}
