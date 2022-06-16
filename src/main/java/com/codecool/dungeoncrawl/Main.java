package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.DataHub;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.data.WorldInformation;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.display.GraphicsData;
import com.codecool.dungeoncrawl.display.Renderer;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.InitEventHandlers;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.MovementEngine;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import com.codecool.dungeoncrawl.util.GameInformation;
import com.codecool.dungeoncrawl.util.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;


public class Main extends Application {
    static MovementEngine movementEngine;
    static Display display;
    AssetCollection assetCollection = new AssetCollection();
    EventEngine eventEngine;
    PhysEngine physEngine;
    GameMap map;

    {
        int firstLevelToLoad = 1;
        map = GameManager.loadMap(assetCollection, firstLevelToLoad);
    }

    Renderer renderer = new Renderer();

    Canvas canvas = GameManager.getCanvas(map);

    GraphicsContext context = GameManager.getGraphicsContext(canvas);

    public static void main(String[] args) {
        launch(args);

    }

    public static void turn() {
        movementEngine.moveAssets();
        display.drawMainGame();
        // System.out.println("Main game drawn");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = GameManager.getNewGridPane(200, 10);

        BorderPane borderPane = GameManager.getNewBorderPaneWithCanvasCenteredAndGridPaneRight(canvas, ui);

        Scene scene = GameManager.getNewScene(borderPane);
        primaryStage.setScene(scene);
        renderer.getMapTiles(assetCollection.getAssets(), context, canvas);

        GameInformation gameInformation = new GameInformation(canvas, context, assetCollection, map, ui, borderPane);


        //TODO NEED ALL DATA HERE
        Player player = assetCollection.getPlayer().get();
        GameData gameData = new GameData(assetCollection, player);
        //*****************   DRAWING   *****************
        display = new Display(gameInformation);
        display.drawMainGame();
        Label healthSection = display.initializeHealthProgressBar();
        Label attackPointsSection = display.initializeAttackPointsProgressBar();
        display.showSpacesBetweenInfoboxContent(7, 5);
        Label inventorySection = display.showAndGetNewLabelAlignedLeft("Inventory: \n", 13);
        display.showNewInformationUnderLabel(player.getInventory().toString(), inventorySection);
        display.showSpacesBetweenInfoboxContent(10, 14);
        Label hintSection = display.showAndGetNewLabelAlignedLeft("Game hints: \n", 15);
        display.showSpacesBetweenInfoboxContent(5, 17);
        Label buttonLabel = display.showAndGetNewLabelAlignedLeft("Buttons: \n", 23);
        Button pickUpButton = display.addButtonUnderLabel(buttonLabel, "Pick up");
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setDisable(true);


        List<Label> labels = Arrays.asList(healthSection, attackPointsSection, inventorySection,
                hintSection, buttonLabel);
        List<Button> buttons = List.of(pickUpButton);
        //*****************   DRAWING DONE   *****************

        //init EventEngine
        eventEngine = EventEngine.getInstance();
        eventEngine.setHandlers(new InitEventHandlers(display, labels, buttons,
                gameInformation.getAssetCollection(),
                gameData, gameInformation).getGameEventHandlers());


        WorldInformation worldInformation = new WorldInformation(
                0,
                0,
                map.getWidth() - 1,
                map.getHeight() - 1);
        PhysEngine.setPhysEngine(gameData, worldInformation);
        DataHub.setGameData(gameData);
        UserInput userInput = new UserInput(gameData, eventEngine);
        scene.setOnKeyPressed(userInput::onKeyPressed);
        gameInformation.setUserInput(userInput);
        gameInformation.setDisplay(display);
        gameInformation.setGameData(gameData);


        //Init MovementEngine
        movementEngine = new MovementEngine(gameInformation.getGameData(), PhysEngine.getEngine(), eventEngine);

        display.drawMainGame();


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
}
