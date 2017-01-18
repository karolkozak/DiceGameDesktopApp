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
public class RequestControllerMocked implements Requestable {

    public static JmsTemplate jmsTemplate;

    @Override
    public List<Game> getGames() {

        String nick = Account.getInstance().getNick();
        String toSend = new Gson().toJson(nick);


        ///mock wysylam sobie jakas gre
        int gameID =124;
        GameType gType = GameType.N_PLUS;
        Integer lPlaces = 10;
        List<Player> lPlayers = new ArrayList<Player>();
        lPlayers.add(new Player(nick));
        lPlayers.add(new Player("yoloGamble"));
        GameState gameState = new GameState(lPlayers,null,null,0,0);
        Game game = new Game(gameID,gType,lPlaces,gameState);

        List<Game> toSendArray = new ArrayList<>();
        toSendArray.add(game);
        toSend = new Gson().toJson(toSendArray);
        //

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<List<Game>> waitOnQueue = es.submit(new Callable<List<Game>>() {
            public List<Game> call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("getGames").toString();// <- getGames/nick
                List<Game> response = new Gson().fromJson(received,new TypeToken<List<Game>>(){}.getType());
                System.out.println(response);
                return response;
            }
        });

        System.out.println(toSend);
        jmsTemplate.convertAndSend("getGames",toSend);

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
        String toSend = new Gson().toJson(loginContainer); // <-
        System.out.println(toSend);
        toSend = new Gson().toJson(new Boolean(true));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("login").toString();// <- login + hash
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("login",toSend);

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
    public boolean makeMove(Move move) {
        MakeMoveContainer makeMoveContainer = new MakeMoveContainer(Account.getInstance().getNick(), move);
        String toSend = new Gson().toJson(makeMoveContainer);
        System.out.println(toSend);
        toSend = new Gson().toJson(new Boolean(true));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("makeMove").toString();// <-  + nick
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("makeMove",toSend);

        Boolean response= false;

        try {
            response = waitOnQueue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdown();

        //mock update
        List<Player> playersList= new ArrayList<Player>();
        playersList.add(new Player(Account.getInstance().getNick(),12,new ArrayList<Integer>(Arrays.asList(new Integer[]{1,4,3,2,5}))));
        playersList.add(new Player("yoloGamble",12,new ArrayList<Integer>(Arrays.asList(new Integer[]{1,4,3,2,5}))));
        GameState gameState =new GameState(playersList, GameStatus.STARTED, "yoloGamble", 0, 0);

        toSend = new Gson().toJson(gameState);
        jmsTemplate.convertAndSend("updateGame", toSend);
        //

        return response;
    }

    @Override
    public boolean joinAsPlayer(int gameID) {

        JoinContainer joinContainer = new JoinContainer(Account.getInstance().getNick(), gameID);
        String toSend = new Gson().toJson(joinContainer);
        System.out.println(toSend);
        toSend = new Gson().toJson(new Boolean(true));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("joinAsPlayer").toString();// <-  + nick
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("joinAsPlayer",toSend);

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
        String toSend = new Gson().toJson(joinContainer); // <-
        System.out.println(toSend);
        toSend = new Gson().toJson(new Boolean(true));

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("spectateGame").toString();// <-  + nick
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("spectateGame",toSend);

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
        String toSend = new Gson().toJson(joinContainer);
        System.out.println(toSend);
        jmsTemplate.convertAndSend("quitGame",toSend);
        return true;
    }

    @Override
    public boolean createGame(Configuration config) {

        CreateGameContainer createContainer = new CreateGameContainer(Account.getInstance().getNick(), config);
        String toSend = new Gson().toJson(createContainer);
        System.out.println(toSend);
        toSend = new Gson().toJson(new Boolean(true));// mock

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<Boolean> waitOnQueue = es.submit(new Callable<Boolean>() {
            public Boolean call() throws Exception {
                String received = jmsTemplate.receiveAndConvert("createGame").toString();// <-  + nick
                boolean response = new Gson().fromJson(received,Boolean.class);
                System.out.println(response);
                return response;
            }
        });

        jmsTemplate.convertAndSend("createGame",toSend);

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
                String received = jmsTemplate.receiveAndConvert("updateGame").toString();// <-  + nick
                GameState response = new Gson().fromJson(received,GameState.class);
                System.out.println(response);
                return response;
            }
        });

        if(mock!=0) {
        //mocked server response
            List<Player> playersList= new ArrayList<Player>();
            playersList.add(new Player(Account.getInstance().getNick(),12,new ArrayList<Integer>(Arrays.asList(new Integer[]{1,4,3,2,5}))));
            playersList.add(new Player("yoloGamble",12,new ArrayList<Integer>(Arrays.asList(new Integer[]{1,4,3,2,5}))));
            GameState gameState = null;
            if (mock == 1)
                gameState = new GameState(playersList, GameStatus.STARTED, Account.getInstance().getNick(), 0, 0);
            if (mock == 2)
                gameState = new GameState(playersList, GameStatus.STOPPED, Account.getInstance().getNick(), 0, 0);
            String toSend = new Gson().toJson(gameState);
            jmsTemplate.convertAndSend("updateGame", toSend);
        }
        //

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
