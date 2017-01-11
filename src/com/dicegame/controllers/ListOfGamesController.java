package com.dicegame.controllers;

import com.dicegame.model.Game;
import com.dicegame.model.GameType;
import com.dicegame.model.ListOfGamesTable;
import com.dicegame.model.Player;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Karol on 07/12/2016.
 */
public class ListOfGamesController implements Initializable {

    @FXML
    private Button update;

    @FXML
    private TableView listOfGames;

    @FXML
    private TableColumn<ListOfGamesTable, GameType> gameTypeColumn;

    @FXML
    private TableColumn<ListOfGamesTable, String> leftPlacesColumn;

    @FXML
    private TableColumn<ListOfGamesTable, ObservableList> listOfPlayersColumn;

    private ObservableList<ListOfGamesTable> games = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameTypeColumn.setCellValueFactory(dataValue -> dataValue.getValue().getGameTypeProperties());
        leftPlacesColumn.setCellValueFactory(dataValue -> dataValue.getValue().getLeftPlacesProperties());
        listOfPlayersColumn.setCellValueFactory(dataValue -> dataValue.getValue().getListOfPlayersProperties());

        listOfGames.setEditable(false);
        listOfGames.setItems(games);

        //Initialize action is same as update
        handleUpdateAction(new ActionEvent());
    }

    @FXML
    public void handleUpdateAction(ActionEvent actionEvent) {
        //TODO: fetch data from server and show in table
        List<Game> fetchedFromServer = new ArrayList<>();

        //remove previous rows
        listOfGames.getItems().removeAll(listOfGames.getItems());

        //add games getched from server to table
        fetchedFromServer.forEach(game -> {
            GameType gameType = game.getGameType();
            Integer leftPlaces = game.getPlacesLeft();
            List<Player> listOfPlayers = game.getGameState().getListOfPlayers();
            games.add(new ListOfGamesTable(gameType, leftPlaces, listOfPlayers));
        });
        listOfGames.setItems(games);

        //add sample row
        GameType gType = GameType.N_PLUS;
        Integer lPlaces = 10;
        List<Player> lPlayers = new ArrayList<Player>();
        lPlayers.add(new Player("Bolek"));
        games.add(new ListOfGamesTable(gType, lPlaces, lPlayers));
        listOfGames.setItems(games);
    }

    @FXML
    public void handleJoinAction(ActionEvent actionEvent) throws IOException {
        listOfGames.getSelectionModel().getSelectedItem();

        System.out.println(listOfGames.getSelectionModel().getSelectedItem().toString());
        //TODO: set the way of game: player/observer and pass to inGame.fxml in order to disable some things

        Parent createGame = FXMLLoader.load(getClass().getResource("../view/inGame.fxml"));
        Scene home_page = new Scene(createGame);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }

    @FXML
    public void handleWatchAction(ActionEvent actionEvent) throws IOException {
        //TODO: set the way of game: player/observer and pass to inGame.fxml in order to disable some things

        Parent createGame = FXMLLoader.load(getClass().getResource("../view/inGame.fxml"));
        Scene home_page = new Scene(createGame);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }

    @FXML
    public void handleCreateGameAction(ActionEvent actionEvent) throws IOException {
        Parent createGame = FXMLLoader.load(getClass().getResource("../view/createGame.fxml"));
        Scene home_page = new Scene(createGame);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }
}
