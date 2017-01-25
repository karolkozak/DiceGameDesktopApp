package com.dicegame.model.containers;

import com.dicegame.model.Player;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Jakub on 2017-01-25.
 */
public class InGameRow {

    private Player player;
    private String active;

    public InGameRow(Player player, String active) {
        this.player = player;
        this.active = active;
    }


    public SimpleStringProperty getPlayerNameProperties() {
        return new SimpleStringProperty(player.getName());
    }

    public SimpleStringProperty getPointsProperties() {
        return new SimpleStringProperty(String.valueOf(player.getPoints()));
    }

    public SimpleStringProperty getListOfDiceProperties() {
        return new SimpleStringProperty(player.getDice().toString());
    }

    public SimpleStringProperty getActiveProperties() {
        return new SimpleStringProperty(active);
    }
}

