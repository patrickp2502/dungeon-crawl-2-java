package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.scenery.Floor;
import com.codecool.dungeoncrawl.logic.scenery.Wall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static Image getTileset() {
        return tileset;
    }

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("sword", new Tile(30, 1));
        tileMap.put("door closed", new Tile(11, 11));
        tileMap.put("enemy robot", new Tile(31, 6));
        tileMap.put("door open", new Tile(12, 11));
        tileMap.put("ghost", new Tile(27, 6));
    }

    public static Tile getTile(Asset asset) {
        Tile tile = asset != null ? tileMap.get(asset.getTileName()) : tileMap.get("empty");
        return tile;
    }
}
