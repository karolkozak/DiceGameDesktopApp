package com.dicegame.model;

import java.util.Set;

/**
 * Created by Karol on 13/12/2016.
 */
public class Move {

    private MoveType moveType;
    private Set<Integer> diceNumbers;

    public Move(MoveType moveType, Set<Integer> diceNumbers) {
        this.moveType = moveType;
        this.diceNumbers = diceNumbers;
    }
}
