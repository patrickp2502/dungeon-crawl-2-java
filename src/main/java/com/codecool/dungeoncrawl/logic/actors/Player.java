package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.display.Tiles;
import com.codecool.dungeoncrawl.logic.movement.PlayerMovement;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public boolean isCollectable() {
        return false;
    }

    @Override
    public boolean isCollideable() {
        return false;
    }

    @Override
    public void setCell(Cell newCell) {

    }

    @Override
    public Tiles.Tile getTile() {
        return null;
    }

}
