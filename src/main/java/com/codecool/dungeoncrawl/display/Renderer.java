package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Translates Data to View
 */
public class Renderer {

    public List<Tiles.Tile> getMapTiles(List<Asset> assets, GraphicsContext context, Canvas canvas) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        List<Tiles.Tile> tiles = new ArrayList<Tiles.Tile>();
        for (Asset asset : assets) {
            Tiles.Tile newTile = Tiles.getTile(asset);
            tiles.add(newTile);
        }
        return tiles;
    }


    public String getHealthLabelText(Player player, Label healthLabel) {
        return "" + player; // .getHealth();
    }
}
