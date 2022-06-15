package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.display.Tiles;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.MapSafer;
import javafx.scene.canvas.Canvas;

import java.util.List;

public class GameManager {

    public static final String LEVELS_PATH = "src/main/resources/levels";

    public static final List<String> AVAILABLE_LEVELS = FileDetector.getAvailableFileNamesInResources();

    public static final MapLoader MAP_LOADER = new MapLoader();

    public static final MapSafer MAP_SAFER = new MapSafer();

    public static Canvas canvas;

    public static GameMap loadNextLevel(AssetCollection assetCollection, GameMap map) {
        // safe old Map
        MAP_SAFER.safeMap(map, assetCollection);
        // delete old Map (GridPane)

        // load and return new Map
        int currentLevel = map.getLevel();
        int level = currentLevel < AVAILABLE_LEVELS.size() ? currentLevel : 1;
        return loadMap(assetCollection, level);
    }

    public static GameMap loadMap(AssetCollection assetCollection, int level) {
        String filePath = getFilePathByIntLevel(level);
        return MAP_LOADER.loadMap(assetCollection, filePath);
    }

    public static String getFilePathByIntLevel(int level) {
        return AVAILABLE_LEVELS.size() <= level ? AVAILABLE_LEVELS.get(level - 1) : AVAILABLE_LEVELS.get(0);
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
                gameMap.getWidth() * Tiles.TILE_WIDTH,
                gameMap.getHeight() * Tiles.TILE_WIDTH);
    }
}
