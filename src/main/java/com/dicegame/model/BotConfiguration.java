package com.dicegame.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Karol on 13/12/2016.
 */
public class BotConfiguration {

    private String name;
    private BotLevel botLevel;

    public BotConfiguration(String name, BotLevel botLevel) {
        this.name = name;
        this.botLevel = botLevel;
    }

    public String getName(){
        return this.name;
    }

    public SimpleStringProperty getBotNameProperties(){
        return new SimpleStringProperty(name);
    }

    public SimpleObjectProperty getBotLevelProperties(){
        return new SimpleObjectProperty<BotLevel>(botLevel);
    }

}
