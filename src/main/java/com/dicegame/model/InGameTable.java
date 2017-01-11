package com.dicegame.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Karol on 08/01/2017.
 */
public class InGameTable {
    private Player player;
    private ObservableList<Integer> dice;

    public InGameTable(Player player) {
        this.player = player;
        this.dice = FXCollections.observableArrayList();
        player.getDice().forEach(die -> dice.add(die));
    }

    public SimpleStringProperty getPlayerNameProperties() {
        return new SimpleStringProperty(player.getName());
    }

    public SimpleStringProperty getPointsProperties() {
        return new SimpleStringProperty(String.valueOf(player.getPoints()));
    }

    public SimpleObjectProperty getListOfDiceProperties() {
        return new SimpleObjectProperty(dice);
    }
}
