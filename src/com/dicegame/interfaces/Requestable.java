package com.dicegame.interfaces;

/**
 * Created by Jakub on 2016-12-18.
 */
public interface Requestable {

    void getAvailableGames();
    void login(String nick);
    void rollDice();
    //-----------
}
