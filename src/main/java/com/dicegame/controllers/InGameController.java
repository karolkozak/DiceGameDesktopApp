package com.dicegame.controllers;

import com.dicegame.model.GameState;
import com.dicegame.model.InGameTable;
import com.dicegame.model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Karol on 07/01/2017.
 */
public class InGameController implements Initializable {

    @FXML
    private TableView resultsTable;

    @FXML
    private TableColumn<InGameTable, String> playerColumn;

    @FXML
    private TableColumn<InGameTable, ObservableList> diceColumn;

    @FXML
    private TableColumn<InGameTable, String> pointsColumn;

    private ObservableList<InGameTable> gameResults = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPlayerNameProperties());
        diceColumn.setCellValueFactory(dataValue -> dataValue.getValue().getListOfDiceProperties());
        pointsColumn.setCellValueFactory(dataValue -> dataValue.getValue().getPointsProperties());

        resultsTable.setEditable(false);
        resultsTable.setItems(gameResults);
    }

    public void handleSurrenderAction(ActionEvent actionEvent) {
        
    }

    public void handleRollAction(ActionEvent actionEvent) {

    }

    /**
     * this method is called after receive gameState (call update method by server)
     */
    public void updateTable(GameState gameState) {
        List<Player> players = gameState.getListOfPlayers();

        //remove previous state
        resultsTable.getItems().removeAll(resultsTable.getItems());
        players.forEach(player -> {
            gameResults.add(new InGameTable(player));
        });
        resultsTable.setItems(gameResults);
    }
}
