package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.controls.UserInput;
import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.WorldInformation;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.display.Tiles;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.MapSafer;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;
import java.util.Optional;

public class GameManager {

    public static final String LEVELS_PATH = "src/main/resources/levels";

    public static final List<String> AVAILABLE_LEVELS = FileDetector.getAvailableFileNamesInResources();

    public static final MapLoader MAP_LOADER = new MapLoader();

    public static final MapSafer MAP_SAFER = new MapSafer();

    public static GameMap loadNextLevel(GameInformation gameInformation) {
        // preparation
        AssetCollection assetCollection = gameInformation.getAssetCollection();
        GameMap map = gameInformation.getMap();
        UserInput userInput = gameInformation.getUserInput();
        Display display = gameInformation.getDisplay();

        // safe old Map
        MAP_SAFER.safeMap(map, assetCollection);

        // load and return new Map
        int currentLevel = map.getLevel();
        int level = currentLevel < AVAILABLE_LEVELS.size() ? currentLevel + 1 : 1;
        GameMap newMap = loadMap(assetCollection, level);
        gameInformation.setMap(newMap);

        // delete old Map (GridPane)

        List<Window> open = Stage.getWindows().stream().filter(Window::isShowing).toList();
        Stage stage = (Stage) open.get(0).getScene().getWindow();
        GridPane newGridPane = getNewGridPane(200, 10);
        Canvas canvas = getCanvas(newMap);
        BorderPane newBorderPane = getNewBorderPaneWithCanvasCenteredAndGridPaneRight(canvas, newGridPane);
        Scene scene = getNewScene(newBorderPane);
        scene.setOnKeyPressed(userInput::onKeyPressed);
        gameInformation.setScene(scene);
        //stage.setScene(newScene);

        gameInformation.setBorderPane(newBorderPane);
        gameInformation.setGridPane(newGridPane);
        WorldInformation worldInformation = new WorldInformation(
                0,
                0,
                newMap.getWidth() - 1,
                newMap.getHeight() - 1);
        PhysEngine.setPhysEngine(gameInformation, worldInformation);
        display.drawMainGame();

        stage.show();
        return gameInformation.getMap();
    }

    public static GameMap loadMap(AssetCollection assetCollection, int level) {
        if (!assetCollection.getAssets().isEmpty()) {
            Player player = getPlayer(assetCollection.getAssets());
            assetCollection.getAssets().clear();
            assetCollection.getAssets().add(player);
        }
        String filePath = getFilePathByIntLevel(level);
        return MAP_LOADER.loadMap(assetCollection, filePath);
    }


    public static String getFilePathByIntLevel(int level) {
        String fileName = AVAILABLE_LEVELS.size() >= level ? AVAILABLE_LEVELS.get(level - 1) : AVAILABLE_LEVELS.get(0);
        Optional<String> possibleFileName = AVAILABLE_LEVELS
                .stream()
                .filter(file -> file.contains(String.format("%d", level)))
                .findFirst();
        if (possibleFileName.isPresent()) {
            fileName = possibleFileName.get();
        }
        return fileName; // fileName.substring(1, fileName.length() - 1);
    }

    public static int getLevelFromFileName(String fileName) {
        String splitString = fileName.substring(fileName.indexOf("p") + 1);
        splitString = splitString.substring(0, splitString.indexOf("."));
        try {
            return Integer.parseInt(splitString);
        } catch (NumberFormatException exception) {
            throw new RuntimeException("FileName wasn't in the default format!", exception);
        }
    }

    public static Canvas getCanvas(GameMap gameMap) {
        return new Canvas(
                20 * Tiles.TILE_WIDTH,
                20 * Tiles.TILE_WIDTH);
    }

    public static GraphicsContext getGraphicsContext(Canvas canvas) {
        return canvas.getGraphicsContext2D();
    }

    public static GridPane getNewGridPane(double width, double padding) {
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(width);
        gridPane.setPadding(new Insets(padding));
        return gridPane;
    }

    public static BorderPane getNewBorderPaneWithCanvasCenteredAndGridPaneRight(Canvas canvas, GridPane gridPane) {
        BorderPane borderPane = new BorderPane(canvas);
        borderPane.setRight(gridPane);
        return borderPane;

    }

    public static Scene getNewScene(BorderPane borderPane) {
        Scene scene = new Scene(borderPane);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        return scene;
    }

    public static Player getPlayer(List<Asset> assets) {
        return (Player) assets
                .stream()
                .filter(asset -> asset instanceof Player)
                .findFirst()
                .get();
    }

}
