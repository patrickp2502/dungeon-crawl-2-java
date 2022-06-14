package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.*;
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
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    MapLoader mapLoader = new MapLoader();
    String[] FILE_PATHS = {"/map.txt", "/map2.txt"};
//    GameMap map = mapLoader.loadMap(assetCollection, FILE_PATHS[0]);
    GameMap map;

    {
        String firstLevel = FILE_PATHS[0];
        map = mapLoader.loadMap(assetCollection, firstLevel);
    }

    /*ArrayList<String> file_paths = new ArrayList<>();
    file_paths.add("/map.txt");
    file_paths.add("/map2.txt");

    GameMap map = mapLoader.loadMap(assetCollection, file_paths.get(1));*/
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    static Display display;
    Renderer renderer = new Renderer();

    List<Asset> assetList = assetCollection.getAssets();

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
        renderer.getMapTiles(assetList, context, canvas);


        //TODO NEED ALL DATA HERE
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
        Label healthSection = display.showAndGetNewLabelAlignedLeft("Health: ", 0);
        display.showNewInformationUnderLabel("+++++++++--", healthSection);
        display.showSpacesBetweenInfoboxContent(10, 2);
        Label inventorySection = display.showAndGetNewLabelAlignedLeft("Inventory: \n", 13);
        display.showNewInformationUnderLabel(player.getInventory().toString(), inventorySection);
        display.showSpacesBetweenInfoboxContent(10, 14);
        Label hintSection = display.showAndGetNewLabelAlignedLeft("Game hints: \n", 15);
        display.showNewInformationUnderLabel("TEST HINT", hintSection);
        /*Button pickUpButton = display.addButtonUnderLabel(hintSection, "Test Pick Up Button");
        pickUpButton.setDefaultButton(false);*/


        List<Label> labels = Arrays.asList(healthSection, inventorySection, hintSection);
        //*****************   DRAWING DONE   *****************

        eventEngine = EventEngine.getInstance();
        eventEngine.setHandlers(new InitEventHandlers(display, labels).getGameEventHandlers());



        WorldInformation worldInformation = new WorldInformation(
                0,
                0,
                map.getWidth()-1,
                map.getHeight()-1);
        // System.out.println("map.getWidth() = " + map.getWidth());
        PhysEngine.setPhysEngine(gameData, worldInformation);
        DataHub.setGameData(gameData);
        UserInput userInput = new UserInput(gameData, eventEngine);
        scene.setOnKeyPressed(userInput::onKeyPressed);

        display = new Display(graphicsData, gameData);
        display.drawMainGame();





        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    public static void turn() {
        display.drawMainGame();
        // System.out.println("Main game drawn");

    }
}
