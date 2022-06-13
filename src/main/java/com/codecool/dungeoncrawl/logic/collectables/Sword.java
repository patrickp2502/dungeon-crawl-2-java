package com.codecool.dungeoncrawl.logic.collectables;

import com.codecool.dungeoncrawl.data.Asset;

public class Sword extends Asset implements Collectable {

    private boolean pickUpPossible;

    public Sword(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        pickUpPossible = false;
    }

    public boolean isPickUpPossible() {
        return pickUpPossible;
    }

    public void setPickUpPossible(boolean pickUpPossible) {
        this.pickUpPossible = pickUpPossible;
    }
}
