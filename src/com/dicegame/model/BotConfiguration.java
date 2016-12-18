package com.dicegame.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Karol on 13/12/2016.
 */
public class BotConfiguration {

    private String name;
    private String botLevel;

    public BotConfiguration(String name, String botLevel) {
        this.name = name;
        this.botLevel = botLevel;
    }

    public SimpleStringProperty getBotNamePropertie(){
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty getBotLevelPropertie(){
        return new SimpleStringProperty(botLevel);
    }

}
