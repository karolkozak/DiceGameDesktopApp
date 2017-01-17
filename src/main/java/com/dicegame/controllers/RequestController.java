package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import com.dicegame.model.*;
import com.dicegame.model.containers.JoinContainer;
import com.dicegame.model.containers.LoginContainer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 2016-12-18.
 */
public class RequestController implements Requestable {

    public static JmsTemplate jmsTemplate;

    @Override
    public List<Game> getGames() {

        String nick = Account.getInstance().getNick();

        Thread waitOnQueue = new Thread(new Runnable(){
            public void run() {
                System.out.println(jmsTemplate.receiveAndConvert("getGames"));// getGames/nick
            }
        });

        waitOnQueue.start();
        jmsTemplate.convertAndSend("getGames",nick);
        try {
            waitOnQueue.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int gameID =124;
        GameType gType = GameType.N_PLUS;
        Integer lPlaces = 10;
        List<Player> lPlayers = new ArrayList<Player>();
        lPlayers.add(new Player(nick));
        GameState gameState = new GameState(lPlayers,null,null,0,0);
        Game game = new Game(gameID,gType,lPlaces,gameState);

        List<Game> receivedGames = new ArrayList<>();
        receivedGames.add(game);

        return receivedGames;
    }

    @Override
    public boolean login(String nick) {

        String url = "someHash";
        LoginContainer loginContainer = new LoginContainer(nick, url);
        String toSend = new Gson().toJson(loginContainer);

        Thread waitOnQueue = new Thread(new Runnable(){
            public void run() {
                System.out.println(jmsTemplate.receiveAndConvert("login"));// login + hash
            }
        });

        waitOnQueue.start();
        jmsTemplate.convertAndSend("login",toSend);

        try {
            waitOnQueue.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }


    @Override
    public List<Integer> makeMove(Move move) {
        return null;
    }

    @Override
    public boolean joinAsPlayer(Game game) {

        JoinContainer joinContainer = new JoinContainer(Account.getInstance().getNick(), game.getGameID());
        String toSend = new Gson().toJson(joinContainer);

        Thread waitOnQueue = new Thread(new Runnable(){
            public void run() {
                System.out.println(jmsTemplate.receiveAndConvert("joinAsPlayer"));// login + hash
            }
        });

        waitOnQueue.start();
        jmsTemplate.convertAndSend("joinAsPlayer",toSend);

        try {
            waitOnQueue.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean spectateGame(Game game) {

        JoinContainer joinContainer = new JoinContainer(Account.getInstance().getNick(), game.getGameID());
        String toSend = new Gson().toJson(joinContainer);

        Thread waitOnQueue = new Thread(new Runnable(){
            public void run() {
                System.out.println(jmsTemplate.receiveAndConvert("spectateGame"));// +nick
            }
        });

        waitOnQueue.start();
        jmsTemplate.convertAndSend("spectateGame",toSend);

        try {
            waitOnQueue.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
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
