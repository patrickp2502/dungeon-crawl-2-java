package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public record GraphicsData(List<Asset> assets, GraphicsContext context, Canvas canvas, GameMap map,
                           GridPane ui) {
}
