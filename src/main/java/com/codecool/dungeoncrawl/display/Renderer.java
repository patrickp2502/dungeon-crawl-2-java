package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Translates Data to View
 */
public class Renderer {

    public GraphicsContext getMapImage(List<Asset> assets, GraphicsContext context, Canvas canvas) {
        /*context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }*/
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Asset asset : assets) {
            int yCoordinate = asset.getYCoordinate();
            int xCoordinate = asset.getXCoordinate();
            Tiles.drawTile(context, asset, xCoordinate, yCoordinate);
        }
        return context;
    }

    public String getHealthLabelText(Player player, Label healthLabel) {
        return "" + player.getHealth();
    }
}
