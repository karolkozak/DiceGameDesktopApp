package com.dicegame.model;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by Karol on 07/01/2017.
 */
public class ListOfGamesTable {
    private GameType gametType;
    private Integer leftPlaces;
    private ObservableList<Player> observablelistOfPlayers;
    private List<Player> listOfPlayers;


    public ListOfGamesTable(GameType gametType, Integer leftPlaces, List<Player> listOfPlayers) {
        this.gametType = gametType;
        this.leftPlaces = leftPlaces;
        this.listOfPlayers = listOfPlayers;
        observablelistOfPlayers = FXCollections.observableArrayList();
        listOfPlayers.forEach(player -> observablelistOfPlayers.add(player));
    }

    public SimpleObjectProperty getGameTypeProperties() {
        return new SimpleObjectProperty<GameType>(gametType);
    }

    public SimpleStringProperty getLeftPlacesProperties() {
        return new SimpleStringProperty(String.valueOf(leftPlaces));
    }

    public SimpleListProperty getListOfPlayersProperties() {
        return new SimpleListProperty(observablelistOfPlayers);
    }
}
