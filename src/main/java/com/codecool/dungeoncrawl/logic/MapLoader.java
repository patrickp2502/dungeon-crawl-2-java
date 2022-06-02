package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.scenery.Floor;
import com.codecool.dungeoncrawl.logic.scenery.Wall;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public GameMap loadMap(AssetCollection assetCollection) {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height); // , CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    // Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            // cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            // cell.setType(CellType.WALL);
                            Asset wallAsset = new Wall("wall", x, y);
                            assetCollection.addAsset(wallAsset);
                            break;
                        case '.':
                            // cell.setType(CellType.FLOOR);
                            Asset floorAsset = new Floor("floor", x, y);
                            assetCollection.addAsset(floorAsset);
                            break;
                        case 's':
                            // cell.setType(CellType.FLOOR);
                            Asset skeletonAsset = new Skeleton("skeleton", x, y);
                            Floor floorUnderSkeletonAsset = new Floor("floor", x, y);
                            assetCollection.addAsset(skeletonAsset);
                            assetCollection.addAsset(floorUnderSkeletonAsset);
                            break;
                        case '@':
                            // cell.setType(CellType.FLOOR);
                            Player playerAsset = new Player("player", x, y);
                            Floor floorUnderPlayerAsset = new Floor("floor", x, y);
                            assetCollection.addAsset(playerAsset);
                            assetCollection.addAsset(floorUnderPlayerAsset);
                            map.setPlayer(playerAsset);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }



}
