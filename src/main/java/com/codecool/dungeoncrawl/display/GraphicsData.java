package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.GameMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public record GraphicsData(List<Asset> assets, GraphicsContext context, Canvas canvas, GameMap map) {
}
