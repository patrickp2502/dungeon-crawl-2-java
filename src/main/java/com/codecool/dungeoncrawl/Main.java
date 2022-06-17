package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.WorldInformation;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.display.Renderer;
import com.codecool.dungeoncrawl.display.Tiles;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.InitEventHandlers;
import com.codecool.dungeoncrawl.logic.movementengine.MovementEngine;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.logic.userActionEngine.SaveHandler;
import com.codecool.dungeoncrawl.persistance.CrawlerDataBaseManager;
import com.codecool.dungeoncrawl.util.GameInformation;
import com.codecool.dungeoncrawl.util.GameManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
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
    GameMap map;

    {
        int firstLevelToLoad = 1;
        map = GameManager.loadMap(assetCollection, firstLevelToLoad);
    }

    Renderer renderer = new Renderer();
    Canvas canvas = GameManager.getCanvas(map);
    GraphicsContext context = GameManager.getGraphicsContext(canvas);

    public static void exit() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "YOU LOST!!!");
        alert.getDialogPane().setStyle("-fx-font-family: 'serif'");
        alert.showAndWait();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);

    }

    public static void turn() {
        movementEngine.moveAssets();
        display.drawMainGame();
    }

    private Canvas getCanvas(GameMap gameMap) {
        return new Canvas(
                20 * Tiles.TILE_WIDTH,
                20 * Tiles.TILE_WIDTH);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = GameManager.getNewGridPane(200, 10);

        BorderPane borderPane = GameManager.getNewBorderPaneWithCanvasCenteredAndGridPaneRight(canvas, ui);

        Scene scene = GameManager.getNewScene(borderPane);
        primaryStage.setScene(scene);
        renderer.getMapTiles(assetCollection.getAssets(), context, canvas);

        GameInformation gameInformation = new GameInformation(canvas, context, assetCollection, map, ui, borderPane);


        Player player = assetCollection.getPlayer().get();
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
                gameInformation.getAssetCollection(), gameInformation).getGameEventHandlers());


        WorldInformation worldInformation = new WorldInformation(
                0,
                0,
                map.getWidth() - 1,
                map.getHeight() - 1);


        PhysEngine.setPhysEngine(gameInformation, worldInformation);

        CrawlerDataBaseManager dataManager = new CrawlerDataBaseManager(gameInformation);
        SaveHandler saveHandler = new SaveHandler(gameInformation, display, dataManager);
        UserInput userInput = new UserInput(gameInformation, eventEngine, saveHandler);
        scene.setOnKeyPressed(userInput::onKeyPressed);
        gameInformation.setUserInput(userInput);
        gameInformation.setDisplay(display);

        //Init MovementEngine
        movementEngine = new MovementEngine(gameInformation, PhysEngine.getEngine(), eventEngine);

        display.drawMainGame();


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
}
