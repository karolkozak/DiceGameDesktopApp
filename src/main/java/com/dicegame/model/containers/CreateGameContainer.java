package com.dicegame.model.containers;

import com.dicegame.model.Configuration;
import com.dicegame.model.Move;

/**
 * Created by Karol on 11/01/2017.
 */
public class CreateGameContainer {

    private String nick;
    private Configuration configuration;

    public CreateGameContainer(String nick, Configuration configuration) {
        this.nick = nick;
        this.configuration = configuration;
    }
}
