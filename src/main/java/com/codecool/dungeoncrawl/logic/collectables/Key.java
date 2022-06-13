package com.codecool.dungeoncrawl.logic.collectables;

import com.codecool.dungeoncrawl.data.Asset;

public class Key extends Asset implements Collectable {

    private boolean pickUpPossible;

    public Key(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        this.pickUpPossible = false;
    }

    public boolean isPickUpPossible() {
        return pickUpPossible;
    }

    public void setPickUpPossible(boolean pickUpPossible) {
        this.pickUpPossible = pickUpPossible;
    }
}
