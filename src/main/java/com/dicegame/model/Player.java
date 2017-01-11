package com.dicegame.model;

import java.util.List;

/**
 * Created by Karol on 13/12/2016.
 */
public class Player {

    private String name;
    private int points;
    private List<Integer> dice;

    public Player(String name, int points, List<Integer> dice) {
        this.name = name;
        this.points = points;
        this.dice = dice;
    }
}
