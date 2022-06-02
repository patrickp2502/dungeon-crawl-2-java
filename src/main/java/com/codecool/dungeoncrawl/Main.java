package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.DataHub;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.display.GraphicsData;
import com.codecool.dungeoncrawl.display.Renderer;
import com.codecool.dungeoncrawl.display.Tiles;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Moveable;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
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

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    AssetCollection assetCollection = new AssetCollection();
    //List<Scenery> scenery = assetCollection.separate(Scenery.class);

    EventEngine eventEngine;
    MapLoader mapLoader = new MapLoader();
    GameMap map = mapLoader.loadMap(assetCollection);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
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

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

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
        List<Collectable> collectables = assetCollection.separate(Collectable.class);
        List<Moveable> moveables = assetCollection.getMovables();
        EventEngine eventEngine = EventEngine.getInstance();

        GraphicsData graphicsData = new GraphicsData(assetCollection.getAssets(), context, canvas, map,
                scenery, moveables, collectables);
        display = new Display(graphicsData);
        display.drawMainGame();
        GameData gameData = new GameData(assetCollection, player);
        DataHub.setGameData(gameData);
        UserInput userInput = new UserInput(gameData, eventEngine);
        scene.setOnKeyPressed(userInput::onKeyPressed);


        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    public static void turn() {
        display.drawMainGame();
        System.out.println("Main game drawn");
    }
}
