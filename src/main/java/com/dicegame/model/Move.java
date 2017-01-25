package com.dicegame.model;

import java.util.Set;

/**
 * Created by Karol on 13/12/2016.
 */
public class Move {

    private Set<Integer> dice;

    public Move( Set<Integer> dice) {
        this.dice = dice;
    }

    public Set<Integer> getDice() {
        return dice;
    }
}
