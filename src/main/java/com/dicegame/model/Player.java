package com.dicegame.model;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

/**
 * Created by Karol on 13/12/2016.
 */
public class Player {

    private String name;
    private int points;
    private List<Integer> dice;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int points, List<Integer> dice) {
        this.name = name;
        this.points = points;
        this.dice = dice;
    }

    public int getPoints() {
        return points;
    }

    public List<Integer> getDice() {
        return dice;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public SimpleStringProperty getPlayerNameProperties() {
        return new SimpleStringProperty(name);
    }

    public SimpleStringProperty getPointsProperties() {
        return new SimpleStringProperty(String.valueOf(points));
    }

    public SimpleStringProperty getListOfDiceProperties() {
        return new SimpleStringProperty(dice.toString());
    }
}
