package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.DataHub;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.data.WorldInformation;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.display.GraphicsData;
import com.codecool.dungeoncrawl.display.Renderer;
import com.codecool.dungeoncrawl.display.Tiles;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.InitEventHandlers;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.MovementEngine;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import com.codecool.dungeoncrawl.logic.userActionEngine.SaveHandler;
import com.codecool.dungeoncrawl.persistance.CrawlerDataBaseManager;
import com.codecool.dungeoncrawl.util.FileDetector;
import javafx.application.Application;
import javafx.geometry.Insets;
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
    AssetCollection assetCollection = new AssetCollection();

    EventEngine eventEngine;
    PhysEngine physEngine;
    static MovementEngine movementEngine;
    MapLoader mapLoader = new MapLoader();
    List<String> levels = FileDetector.getAvailableFileNamesInResources();
    GameMap map;

    {
        String firstLevel = levels.get(0);
        String secondLevel = levels.get(1);
        map = mapLoader.loadMap(assetCollection, secondLevel);
    }


    Canvas canvas = getCanvas(map);

    private Canvas getCanvas(GameMap gameMap) {
        return new Canvas(
                gameMap.getWidth() * Tiles.TILE_WIDTH,
                gameMap.getHeight() * Tiles.TILE_WIDTH);
    }

    GraphicsContext context = canvas.getGraphicsContext2D();
    static Display display;
    Renderer renderer = new Renderer();

    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(scene);
        renderer.getMapTiles(assetCollection.getAssets(), context, canvas);


        Player player = assetCollection.getPlayer().get();
        List<Scenery> scenery = assetCollection.getScenery();
        List<Collectable> collectables = assetCollection.getCollectables();
        List<Moveable> moveables = assetCollection.getMovables();
        GameData gameData = new GameData(assetCollection, player);
        //*****************   DRAWING   *****************
        GraphicsData graphicsData = new GraphicsData(assetCollection.getAssets(), context, canvas, map,
                scenery, moveables, collectables, ui);
        display = new Display(graphicsData, gameData);
        display.drawMainGame();
        Label healthSection = display.initializeHealthProgressBar(); // display.showAndGetNewLabelAlignedLeft("Health: ", 0);
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
        eventEngine.setHandlers(new InitEventHandlers(display, labels, buttons, graphicsData.assets(), gameData).getGameEventHandlers());


        WorldInformation worldInformation = new WorldInformation(
                0,
                0,
                map.getWidth() - 1,
                map.getHeight() - 1);


        PhysEngine.setPhysEngine(gameData, worldInformation);
        DataHub.setGameData(gameData);

        CrawlerDataBaseManager dataManager = new CrawlerDataBaseManager(gameData);
        SaveHandler saveHandler = new SaveHandler(gameData, display, dataManager);
        UserInput userInput = new UserInput(gameData, eventEngine, saveHandler);
        scene.setOnKeyPressed(userInput::onKeyPressed);



        display = new Display(graphicsData, gameData);
        //Init MovementEngine
        movementEngine = new MovementEngine(gameData, PhysEngine.getEngine(), eventEngine);

        display.drawMainGame();

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    public static void turn() {
        movementEngine.moveAssets();
        display.drawMainGame();
        // System.out.println("Main game drawn");
    }
}
