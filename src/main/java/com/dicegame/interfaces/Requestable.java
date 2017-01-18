package com.dicegame.interfaces;

import com.dicegame.model.Configuration;
import com.dicegame.model.Game;
import com.dicegame.model.GameState;
import com.dicegame.model.Move;

import java.util.List;

/**
 * Created by Jakub on 2016-12-18.
 */
public interface Requestable {

    List<Game> getGames();
    boolean login(String nick);
    boolean makeMove(Move move);
    boolean joinAsPlayer(int gameID);
    boolean spectateGame(int gameID);
    boolean quitGame(int gameID);
    boolean createGame(Configuration config);
    GameState updateGame();

}
