package com.dicegame.controllers;

import com.dicegame.model.BotConfiguration;
import com.dicegame.model.BotLevel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Karol on 07/12/2016.
 */
public class CreateGameController implements Initializable {

    @FXML
    private ComboBox<String> modeComboBox;
    private ObservableList<String> modeComboBoxItems = FXCollections.observableArrayList();

    @FXML
    private Spinner numberOfPlayersSpinner;

    @FXML
    private ComboBox<String> botLevel;
    private ObservableList<String> botLevelItems = FXCollections.observableArrayList();

    @FXML
    private TableView botsTable;

    @FXML
    private TableColumn<BotConfiguration, String> botNameColumn;

    @FXML
    private TableColumn<BotConfiguration, String> botLevelColumn;

    private ObservableList<BotConfiguration> bots = FXCollections.observableArrayList();


    @FXML
    private TextField botName;

    @FXML
    private TextField points;

    @FXML
    public void handleCreateGameAction(ActionEvent actionEvent) {
        String gameMode = modeComboBox.getValue();
        Integer numOfPlayers = (Integer)numberOfPlayersSpinner.getValue();
    }

    @FXML
    public void handleAddBotAction(ActionEvent actionEvent) {

        String bot = botName.getText();
        String level = botLevel.getValue();

        bots.add(new BotConfiguration("ADAM","EASY"));
        botsTable.setItems(bots);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modeComboBoxItems.add("Poker");
        modeComboBoxItems.add("N+");
        modeComboBoxItems.add("N*");

        modeComboBox.setItems(modeComboBoxItems);

        numberOfPlayersSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 10000, 0));
        numberOfPlayersSpinner.setEditable(true);

        botLevelItems.add("Latwy");
        botLevelItems.add("Mistrz");

        botLevel.setItems(botLevelItems);

        botNameColumn.setCellValueFactory(dataValue -> dataValue.getValue().getBotNamePropertie());
        botLevelColumn.setCellValueFactory(dataValue -> dataValue.getValue().getBotLevelPropertie());


        botsTable.setEditable(false);
        botsTable.setItems(bots);
    }


    @FXML
    public void handleBackAction(ActionEvent actionEvent) throws IOException {
        Parent back = FXMLLoader.load(getClass().getResource("../view/listOfGames.fxml"));
        Scene list_game_page = new Scene(back);
        Stage back_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        back_stage.setScene(list_game_page);
        back_stage.show();
    }
}
