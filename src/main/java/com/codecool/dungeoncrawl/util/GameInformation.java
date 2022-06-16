package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class GameInformation {

    private final Canvas canvas;

    private final GraphicsContext graphicsContext;

    private AssetCollection assetCollection;

    private GameMap map;

    private GridPane gridPane;

    private BorderPane borderPane;

    private Scene scene;

    private UserInput userInput;

    private Display display;

    public GameInformation(Canvas canvas, GraphicsContext graphicsContext, AssetCollection assetCollection,
                           GameMap map, GridPane gridPane, BorderPane borderPane) {
        this.canvas = canvas;
        this.graphicsContext = graphicsContext;
        this.assetCollection = assetCollection;
        this.map = map;
        this.gridPane = gridPane;
        this.borderPane = borderPane;
    }

    public UserInput getUserInput() {
        return userInput;
    }

    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }


    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public AssetCollection getAssetCollection() {
        return assetCollection;
    }

    public void setAssetCollection(AssetCollection assetCollection) {
        this.assetCollection = assetCollection;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

}
