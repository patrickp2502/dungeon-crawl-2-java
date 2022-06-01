package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.display.Renderer;
import com.codecool.dungeoncrawl.display.Tiles;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    EventEngine eventEngine;
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();

    Renderer renderer = new Renderer();

    List<Asset> assetList = new ArrayList<Asset>();

    public static void main(String[] args) {
        launch(args);

    }

    public void turn() {
        eventEngine.handle();

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
        renderer.getMapTiles(assetList, context, canvas); // Replaces "refresh();"
        //refresh();
        //scene.setOnKeyPressed(this::onKeyPressed);


        //TODO NEED ALL DATA HERE
        List<Asset> assetCollection = new ArrayList<>();
        Player player = new Player("player", 10, 10);
        EventEngine eventEngine = new EventEngine();
        GameData gameData = new GameData(assetCollection, player);
        UserInput userInput = new UserInput(gameData, eventEngine);
        scene.setOnKeyPressed(userInput::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }


    /*private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }*/
}
