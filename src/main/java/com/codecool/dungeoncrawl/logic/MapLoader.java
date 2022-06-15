package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.logic.actors.FatDude;
import com.codecool.dungeoncrawl.logic.actors.Goblin;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.collectables.Key;
import com.codecool.dungeoncrawl.logic.collectables.Sword;
import com.codecool.dungeoncrawl.logic.scenery.DoorClosed;
import com.codecool.dungeoncrawl.logic.scenery.DoorOpened;
import com.codecool.dungeoncrawl.logic.scenery.Floor;
import com.codecool.dungeoncrawl.logic.scenery.Wall;
import com.codecool.dungeoncrawl.util.GameManager;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public GameMap loadMap(AssetCollection assetCollection, String filePath) {
        InputStream is = MapLoader.class.getResourceAsStream(filePath);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int level = GameManager.getLevelFromFileName(filePath);

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, level); // , CellType.EMPTY);
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
                        case '|':
                            DoorClosed doorClosed = new DoorClosed("door closed", x, y);
                            assetCollection.addAsset(doorClosed);
                            break;
                        case '_':
                            DoorOpened doorOpened = new DoorOpened("door open", x, y);
                            assetCollection.addAsset(doorOpened);
                            break;
                        case 'K':
                            Key key = new Key("key", x, y);
                            Floor floorUnderKeyAsset = new Floor("floor", x, y);
                            assetCollection.addAsset(key);
                            assetCollection.addAsset(floorUnderKeyAsset);
                            break;
                        case 'S':
                            Sword sword = new Sword("sword", x, y);
                            Floor floorUnderSwordAsset = new Floor("floor", x, y);
                            assetCollection.addAsset(sword);
                            assetCollection.addAsset(floorUnderSwordAsset);
                            break;
                        case 'f':
                            FatDude fatDude = new FatDude("fat dude", x, y);
                            Floor floorUnderFatDude = new Floor("floor",x,y);
                            assetCollection.addAsset(floorUnderFatDude);
                            assetCollection.addAsset(fatDude);
                            break;
                        case 'g':
                            Goblin goblin = new Goblin("goblin", x, y);
                            Floor floorUnderGoblin = new Floor("floor",x,y);
                            assetCollection.addAsset(goblin);
                            assetCollection.addAsset(floorUnderGoblin);
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
