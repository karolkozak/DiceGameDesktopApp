package com.dicegame.controllers;

import com.dicegame.interfaces.Requestable;
import com.dicegame.model.Account;
import com.dicegame.model.GameState;
import com.dicegame.model.Move;
import com.dicegame.model.Player;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Karol on 07/01/2017.
 */
public class InGameController implements Initializable {

    @FXML
    private TableView<Player> resultsTable;
    @FXML
    private TableColumn<Player, String> playerColumn;
    @FXML
    private TableColumn<Player, String> diceColumn;
    @FXML
    private TableColumn<Player, String> pointsColumn;
    @FXML
    private CheckBox box1;
    @FXML
    private CheckBox box2;
    @FXML
    private CheckBox box3;
    @FXML
    private CheckBox box4;
    @FXML
    private CheckBox box5;
    @FXML
    private Button roll;
    @FXML
    private Button pass;
    @FXML
    private Button surrender;

    private ObservableList<Player> playersInGame = FXCollections.observableArrayList();
    private Requestable serverCommunicator;
    private GameState gameState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPlayerNameProperties());
        diceColumn.setCellValueFactory(dataValue -> dataValue.getValue().getListOfDiceProperties());
        pointsColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPointsProperties());

        resultsTable.setEditable(false);
        resultsTable.setItems(playersInGame);

        gameplay();
    }

    public void handleSurrenderAction(ActionEvent actionEvent) throws IOException {
        serverCommunicator = new RequestController();
        serverCommunicator.quitGame(Account.getInstance().getGameID());
        Account.getInstance().setGameID(0);

        Parent createGame = FXMLLoader.load(getClass().getResource("../view/listOfGames.fxml"));
        Scene home_page = new Scene(createGame);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }

    public void handleRollAction(ActionEvent actionEvent) {
        Set<Integer> move = new HashSet<Integer>();
        if(box1.isSelected()) move.add(1);
        if(box2.isSelected()) move.add(2);
        if(box3.isSelected()) move.add(3);
        if(box4.isSelected()) move.add(4);
        if(box5.isSelected()) move.add(5);

        if(!move.isEmpty()) {
            serverCommunicator = new RequestController();
            serverCommunicator.makeMove(new Move(move));
        }
    }

    public void handlePassAction(ActionEvent actionEvent) {
        Set<Integer> move = new HashSet<Integer>();
        serverCommunicator = new RequestController();
        serverCommunicator.makeMove(new Move(move));
    }

    public void gameplay(){
        //while not finished update;
    }

    /**
     * this method is called after receive gameState (call update method by server)
     */
    public void updateTable(GameState gameState) {
        List<Player> players = gameState.getListOfPlayers();

        //remove previous state
        resultsTable.getItems().removeAll(resultsTable.getItems());
        players.forEach(player -> {
            this.playersInGame.add(player);
        });
        resultsTable.setItems(this.playersInGame);
    }
}
