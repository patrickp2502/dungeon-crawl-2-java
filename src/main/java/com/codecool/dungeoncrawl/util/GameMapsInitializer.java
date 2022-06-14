package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;

import java.util.ArrayList;
import java.util.List;

public class GameMapsInitializer {
    public static List<GameMap> getAllAvailableGameMaps(MapLoader mapLoader, List<String> levels,
                                                        AssetCollection assetCollection) {
        List<GameMap> gameMaps = new ArrayList<>();
        for (String level: levels) {
            gameMaps.add(mapLoader.loadMap(assetCollection, level));
        }
        return gameMaps;
    }

    public static GameMap loadNextLevel(MapLoader mapLoader, String level, AssetCollection assetCollection) {
        return mapLoader.loadMap(assetCollection, level);
    }
}
