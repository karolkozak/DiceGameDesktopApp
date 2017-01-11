package com.dicegame.controllers;

import com.dicegame.interfaces.Connectable;
import com.dicegame.model.GameState;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jakub on 2016-12-20.
 */
@Component
public class ConnectController implements Connectable {

    @JmsListener(destination = "receiveMessage/kozak", containerFactory = "myFactory")
    public void receiveMessage(String email) {
        System.out.println("Received <" + email + ">");
    }

    @Override
    public void updateGame(GameState gameState) {

    }
}
