package com.codecool.dungeoncrawl.display;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.collections.Inventory;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import com.codecool.dungeoncrawl.util.AssetUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codecool.dungeoncrawl.display.Tiles.getTile;

/**
 * Only exit point to display everything
 */
public class Display {
    private final GraphicsData graphicsData;

    private final GameData gameData;

    private final ProgressBar healthBar;

    private StringBuilder stringBuilder;

    public Display(GraphicsData graphicsData, GameData gameData) {
        this.graphicsData = graphicsData;
        this.gameData = gameData;
        stringBuilder = new StringBuilder();
        healthBar = new ProgressBar();
    }

    public Label initializeHealthProgressBar() {
        Label healthLabelCreated = showAndGetNewLabelAlignedLeft("Health: \n", 0);
        addProgressBarUnderLabel(healthLabelCreated, healthBar);

        healthBar.setProgress(1);
        return healthLabelCreated;
    }

    public Label initializeAttackPointsProgressBar() {
        Label attackPointsLabel = showAndGetNewLabelAlignedLeft("Attack Points: ", 4);
        setAttackPointsLabel(attackPointsLabel);
        return attackPointsLabel;
    }

    public void setAttackPointsLabel(Label attackPointsLabel) {
        String currentText = attackPointsLabel.getText();
        String[] textLines = currentText.split(":");
        int attackPoints = gameData.player().getCombatStats().getAttackPoints();
        attackPointsLabel.setText(textLines[0] + ": " + attackPoints);
    }

    public void setHealthProgressBar(Player player) {
        double playerHealth = player.getCombatStats().getHealth();
        double playerMaxHealth = player.getMAX_HEALTH();
        double healthPercentage = playerHealth/playerMaxHealth ;
        healthBar.setProgress(healthPercentage);
    }

    public void drawMainGame() {
        GraphicsContext context = graphicsData.context();
        List<Asset> scenery = graphicsData.assets()
                .stream()
                .filter(asset -> asset instanceof Scenery)
                .collect(Collectors.toList());
        List<Asset> collectables = graphicsData.assets()
                .stream()
                .filter(asset -> asset instanceof Collectable)
                .collect(Collectors.toList());
        List<Asset> moveables = graphicsData.assets()
                .stream()
                .filter(asset -> asset instanceof Moveable)
                .collect(Collectors.toList());
        List<Asset> assets = graphicsData.assets();
        Canvas canvas = graphicsData.canvas();
        GameMap map = graphicsData.map();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawFirstLayer(map, context);
        iterateAndDraw(scenery, map, context);
        iterateAndDraw(collectables, map, context);
        iterateAndDraw(moveables, map, context);
    }

    private <T> void iterateAndDraw(List<T> assets, GameMap map, GraphicsContext context) {
        //TODO NEED TO REFACTOR!
        int playerX = gameData.player().getXCoordinate();
        int playerY = gameData.player().getYCoordinate();
        int playerXstart = 10;
        int playerYstart = 9;
        int differenceX = playerXstart - playerX;
        int differenceY = playerYstart - playerY;

        int maxX = map.getWidth();
        int maxY = map.getHeight();
        int minX = 0;
        int minY = 0;


        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                Asset actual = AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).size() > 0 ?
                        AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).get(0) : null;
                if (actual != null && AssetUtil.getAssetByCoordinates((List<Asset>) assets, x, y).size() == 1) {
                    drawTile(context, getTile(actual), x+differenceX, y+differenceY);
                }
            }
        }
    }

    public void drawFirstLayer(GameMap map, GraphicsContext context) {
    //TODO NEED TO REFACTOR!
        int playerX = gameData.player().getXCoordinate();
        int playerY = gameData.player().getYCoordinate();

        int maxX = map.getWidth()+10;
        int maxY = map.getHeight()+10;
        int minX = 0;
        int minY = 0;
        int playerXstart = 5;
        int playerYstart = 9;
        int differenceX = playerXstart - playerX;
        int differenceY = playerYstart - playerY;
        System.out.println("playerY = " + playerY);
        System.out.println("playerX = " + playerX);



        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                drawTile(context, getTile(null), x+differenceX, y+differenceY);
            }
        }
    }

    public void drawTile(GraphicsContext context, Tiles.Tile tile, int x, int y) {
        context.drawImage(Tiles.getTileset(), tile.x, tile.y, tile.w, tile.h,
                x * Tiles.TILE_WIDTH, y * Tiles.TILE_WIDTH, Tiles.TILE_WIDTH, Tiles.TILE_WIDTH);
    }

    public void showNewInformationUnderLabel(String hint, Label label) {
        label.setText(label.getText() + hint);
    }

    public void showSpacesBetweenInfoboxContent(int emptyLines, int startRow) {
        stringBuilder = new StringBuilder();
        IntStream
                .range(0, emptyLines)
                .forEach(line -> stringBuilder.append(" \n"));
        graphicsData.ui().add(new Label(stringBuilder.toString()), 0, startRow);
    }

    public Label showAndGetNewLabelAlignedLeft(String labelText, int rowOfLabel) {
        Label label = new Label(labelText);
        graphicsData.ui().add(label, 0, rowOfLabel);
        return label;
    }

    public void drawInventory(Label inventorySection) {
        Player player = (Player) graphicsData.moveables()
                .stream()
                .filter(gamer -> gamer instanceof Player)
                .findAny().get();

        Inventory inventory = player.getInventory();
        inventorySection.setText("Inventory:\n" + inventory.toString());
    }

    public Button addButtonUnderLabel(Label label, String buttonLabel) {
        Button button = new Button(buttonLabel);
        double layoutX = 0; // label.getLayoutX();
        double layoutY = 25; // label.getLayoutY();
        graphicsData.ui().add(button, (int) layoutX, (int) layoutY);
        return button;
    }

    public void addProgressBarUnderLabel(Label label, ProgressBar progressBar) {
        double layoutX = 0; // label.getLayoutX();
        double layoutY = label.getLayoutY() + 1; // label.getLayoutY();
        graphicsData.ui().add(progressBar, (int) layoutX, (int) layoutY);
    }

    public void resetLabel(Label label) {
        label.setText(label.getText().split("\n")[0]);
    }

    public void resetPreviousLine(Label label) {
        String[] lines = label.getText().split("\n");
        String text = lines.length > 2 ? lines[0] + "\n" + lines[2] : String.join("\n", lines);
        label.setText(text);
    }

}