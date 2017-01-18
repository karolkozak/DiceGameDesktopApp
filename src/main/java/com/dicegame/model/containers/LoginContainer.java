package com.dicegame.model.containers;

/**
 * Created by Karol on 11/01/2017.
 */
public class LoginContainer {

    private String nick;
    private String respondQueue;

    public LoginContainer(String nick, String url) {
        this.nick = nick;
        this.respondQueue = url;
    }
}
