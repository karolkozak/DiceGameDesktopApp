package com.dicegame.model;

/**
 * Created by Jakub on 2017-01-03.
 */
public class Account {

    private String nick;
    private int gameID;
    private static Account instance = null;

    private Account(){}

    public static Account getInstance(){

        if(instance == null) {
            instance = new Account();

        }

        return instance;
    }

    public String getNick() {
        return instance.nick;
    }

    public void setNick(String nick) {
        instance.nick = nick;
    }

    public int getGameID() {
        return instance.gameID;
    }

    public void setGameID(int gameID) {
        this.instance.gameID = gameID;
    }
}
