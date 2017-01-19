package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import com.dicegame.model.*;
import com.dicegame.model.containers.CreateGameContainer;
import com.dicegame.model.containers.JoinContainer;
import com.dicegame.model.containers.LoginContainer;
import com.dicegame.model.containers.MakeMoveContainer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Jakub on 2016-12-18.
 */
public class RequestController implements Requestable {

    public static JmsTemplate jmsTemplate;

    @Override
    public List<Game> getGames() {

        String nick = Account.getInstance().getNick();
        System.out.println(new Gson().toJson(nick));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<List<Game>> waitOnQueue = es.submit(new Callable<List<Game>>() {
            public List<Game> call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("getGames/".concat(nick)).toString();
                System.out.println(received);
                List<Game> response = new Gson().fromJson(received,new TypeToken<List<Game>>(){}.getType());
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("getGames",nick);

        List<Game> response= new ArrayList<>();
        try {
            response = waitOnQueue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();

        return response;
    }

    @Override
    public boolean login(String nick) {

        String url = "someHash";
        LoginContainer loginContainer = new LoginContainer(nick, url);
        System.out.println(new Gson().toJson(loginContainer));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert(url).toString();// <- login + hash
                System.out.println(received);
                Boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("login",loginContainer);

        Boolean response= false;

        try {
            response = waitOnQueue.get();
        } catch (Exception e) {
            System.out.println(e);
        }
        es.shutdown();

        return response;
    }

    @Override
    public boolean makeMove(Move move) {
        MakeMoveContainer makeMoveContainer = new MakeMoveContainer(Account.getInstance().getNick(), move);
        System.out.println(new Gson().toJson(makeMoveContainer));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("makeMove/".concat(Account.getInstance().getNick())).toString();
                System.out.println(received);
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("makeMove",makeMoveContainer);

        Boolean response= false;

        try {
            response = waitOnQueue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();

        return response;
    }

    @Override
    public boolean joinAsPlayer(int gameID) {

        JoinContainer joinContainer = new JoinContainer(Account.getInstance().getNick(), gameID);
        System.out.println(new Gson().toJson(joinContainer));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("joinAsPlayer/".concat(Account.getInstance().getNick())).toString();
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("joinAsPlayer",joinContainer);
        Boolean response= false;

        try {
            response = waitOnQueue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();

        return response;
    }

    @Override
    public boolean spectateGame(int gameID) {

        JoinContainer joinContainer = new JoinContainer(Account.getInstance().getNick(), gameID);
        System.out.println(new Gson().toJson(joinContainer));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("spectateGame/".concat(Account.getInstance().getNick())).toString();
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("spectateGame",joinContainer);

        Boolean response= false;

        try {
            response = waitOnQueue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();

        return response;
    }

    @Override
    public boolean quitGame(int gameID) {
        JoinContainer joinContainer = new JoinContainer(Account.getInstance().getNick(), gameID);
        System.out.println(new Gson().toJson(joinContainer));
        jmsTemplate.convertAndSend("quitGame",joinContainer);
        return true;
    }

    @Override
    public boolean createGame(Configuration config) {

        CreateGameContainer createContainer = new CreateGameContainer(Account.getInstance().getNick(), config);
        System.out.println(new Gson().toJson(createContainer));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("createGame/".concat(Account.getInstance().getNick())).toString();// <-  + nick
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("createGame",createContainer);

        Boolean response= false;

        try {
            response = waitOnQueue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();

        return response;
    }

    @Override
    public GameState updateGame(int mock) {


        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<GameState> waitOnQueue = es.submit(new Callable<GameState>() {
            public GameState call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("updateGame/".concat(Account.getInstance().getNick())).toString();// <-  + nick
                System.out.println(received);
                GameState response = new Gson().fromJson(received,GameState.class);
                System.out.println(response);
                return response;
            }
        });


        GameState response = null;

        try {
            response = waitOnQueue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();

        return response;
    }


}
