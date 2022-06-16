package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Player;

public class GameMap {
    private int width;
    private int height;

    public int getLevel() {
        return level;
    }

    private final int level;

    private Player player;

    public GameMap(int width, int height, int level) {
        this.width = width;
        this.height = height;
        this.level = level;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
