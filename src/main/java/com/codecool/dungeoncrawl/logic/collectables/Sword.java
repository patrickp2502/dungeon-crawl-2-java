package com.codecool.dungeoncrawl.logic.collectables;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;

public class Sword extends Asset implements Collectable {

    private boolean pickUpPossible;

    private CombatStats combatStats;

    public Sword(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        pickUpPossible = false;
        combatStats = new CombatStats(100, 2);
    }

    public CombatStats getCombatStats() {
        if (combatStats == null) {
            throw new IllegalStateException("Set Combatstats first!");
        }
        return combatStats;
    }

    public boolean isPickUpPossible() {
        return pickUpPossible;
    }

    public void setPickUpPossible(boolean pickUpPossible) {
        this.pickUpPossible = pickUpPossible;
    }
}
