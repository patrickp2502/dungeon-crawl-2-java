package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.logic.actors.FatDude;
import com.codecool.dungeoncrawl.logic.actors.Goblin;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.collectables.Key;
import com.codecool.dungeoncrawl.logic.collectables.Sword;
import com.codecool.dungeoncrawl.logic.scenery.*;
import com.codecool.dungeoncrawl.util.GameManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapSafer {

    public void safeMap(GameMap map, AssetCollection assetCollection) {
        int level = map.getLevel();
        String pathName = GameManager.getFilePathByIntLevel(level);
        File file = new File(pathName);
        // write the width and height in the first line
        String firstLine = getWidthAndHeight(map);
        String wholeMap = getMapInStringFormat(map, assetCollection);
        String allTogether = firstLine + wholeMap;
        // draw the board as it should be drawn
        drawStringToFile(allTogether, file);
    }

    private String getMapInStringFormat(GameMap map, AssetCollection assetCollection) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < map.getHeight(); y++) {
            if (y > 0) {
                stringBuilder.append("\n");
            }
            for (int x = 0; x < map.getWidth(); x++) {
                Asset asset = getAssetOnPosition(x, y, assetCollection);
                Character symbol = findCharacterForAsset(asset);
                stringBuilder.append(symbol);
            }
        }


        return stringBuilder.toString();
    }

    private Character findCharacterForAsset(Asset asset) {
        return switch (asset) {
            case Player player -> '@';
            case FatDude fatDude -> 'f';
            case Goblin goblin -> 'g';
            case Skeleton skeleton -> 's';
            case Key key -> 'K';
            case Sword sword -> 'S';
            case DoorClosed doorClosed -> '|';
            case DoorOpened doorOpened -> '_';
            case Empty empty -> ' ';
            case Floor floor -> '.';
            case Wall wall -> '#';
            default -> throw new RuntimeException("Unknown Asset");
        };
    }

    private Asset getAssetOnPosition(int x, int y, AssetCollection assetCollection) {
        for (Asset asset : assetCollection.getAssets()) {
            if (asset.getXCoordinate() == x && asset.getYCoordinate() == y) {
                return asset;
            }
        }
        return new Empty(" ", x, y);
    }

    private String getWidthAndHeight(GameMap map) {
        return map.getWidth() + " " + map.getHeight() + "\n";
    }

    private void drawStringToFile(String string, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(string);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
