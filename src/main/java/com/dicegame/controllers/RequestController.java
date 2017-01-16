package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import com.dicegame.model.Configuration;
import com.dicegame.model.Game;
import com.dicegame.model.Move;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

/**
 * Created by Jakub on 2016-12-18.
 */
public class RequestController implements Requestable {

    public static JmsTemplate jmsTemplate;

    @Override
    public List<Game> getGames() {
        return null;
    }

    @Override
    public boolean login(String nick) {

        new Thread(new Runnable(){
            public void run() {
                System.out.println(jmsTemplate.receiveAndConvert("login"));
            }
        }).start();

        jmsTemplate.convertAndSend("login",nick);
        return true;
    }


    @Override
    public List<Integer> makeMove(Move move) {
        return null;
    }

    @Override
    public boolean joinAsPlayer(Game game) {
        return false;
    }

    @Override
    public boolean spectateGame(Game game) {
        return false;
    }

    @Override
    public boolean quitGame() {
        return false;
    }

    @Override
    public boolean createGame(Configuration config) {
        return false;
    }//JMS SHIT


}
