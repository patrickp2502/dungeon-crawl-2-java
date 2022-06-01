package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.util.AssetUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

import static com.codecool.dungeoncrawl.display.Tiles.getTile;

/**
 * Only exit point to display everything
 */
public class Display {

    private final GraphicsData graphicsData;

    public Display(GraphicsData graphicsData) {
        this.graphicsData = graphicsData;
    }

    public void drawHealth(Label healthLabel, String labelText, GraphicsContext context) {
        healthLabel.setText(labelText);
    }

    public void drawMainGame() {
        GraphicsContext context = graphicsData.context();
        List<Asset> assets = graphicsData.assets();
        Canvas canvas = graphicsData.canvas();
        GameMap map = graphicsData.map();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                if (AssetUtil.getAssetByCoordinates(assets, x, y).size() > 0) {
                    Asset asset = AssetUtil.getAssetByCoordinates(assets, x, y).get(0);
                    drawTile(context, getTile(asset), x, y);
                } else {
                    drawTile(context, getTile(null), x, y);
                }
            }
        }
    }

    public void drawTile(GraphicsContext context, Tiles.Tile tile, int x, int y) {
        context.drawImage(Tiles.getTileset(), tile.x, tile.y, tile.w, tile.h,
                x * Tiles.TILE_WIDTH, y * Tiles.TILE_WIDTH, Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
    }

}
