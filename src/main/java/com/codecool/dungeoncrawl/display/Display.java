package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Moveable;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import com.codecool.dungeoncrawl.util.AssetUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
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

    /*public void drawMainGame() {
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
    }*/

    public void drawMainGame() {
        GraphicsContext context = graphicsData.context();
        List<Scenery> scenery = graphicsData.scenery();
        List<Collectable> collectables = graphicsData.collectables();
        List<Moveable> moveables = graphicsData.moveables();
        List<Asset> assets = graphicsData.assets();
        Canvas canvas = graphicsData.canvas();
        GameMap map = graphicsData.map();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawFirstLayer(map, context);
        iterateAndDraw(scenery, map, context);
        //iterateAndDraw(collectables, map, context);
        iterateAndDraw(moveables, map, context);
    }

    private <T> void iterateAndDraw(List<T> assets, GameMap map, GraphicsContext context) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Asset actual = AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).size() > 0 ?
                        AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).get(0) : null;
                if (actual != null && AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).size() == 1) {
                    drawTile(context, getTile(actual), x, y);
                }            }
        }
    }

    public void drawFirstLayer(GameMap map, GraphicsContext context) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                drawTile(context, getTile(null), x, y);
            }
        }
    }

    public void drawTile(GraphicsContext context, Tiles.Tile tile, int x, int y) {
        context.drawImage(Tiles.getTileset(), tile.x, tile.y, tile.w, tile.h,
                x * Tiles.TILE_WIDTH, y * Tiles.TILE_WIDTH, Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
    }

}

/*
try {
                    switch (asset) {
                        case Scenery assetToDraw -> drawTile(context, getTile((Asset) assetToDraw), x, y);
                        case Collectable assetToDraw -> drawTile(context, getTile((Asset) assetToDraw), x, y);
                        case Moveable assetToDraw -> drawTile(context, getTile((Asset) assetToDraw), x, y);
                        default -> drawTile(context, getTile(null), x, y);
                    }
                } catch (Exception e) {
                    drawTile(context, getTile(null), x, y);
                }
 */