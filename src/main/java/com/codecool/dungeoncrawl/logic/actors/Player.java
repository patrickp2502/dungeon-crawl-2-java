package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.collections.Inventory;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.RandomMovementBehaviour;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class Player extends Asset implements IsSolid, Moveable, Fighter {

    private final Inventory inventory;
    private MovementBehaviour movementBehaviour;
    private CombatStats combatStats;


    public Player(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        movementBehaviour = new RandomMovementBehaviour();
        inventory = new Inventory();
        combatStats = new CombatStats(100, 10);
    }


    @Override
    public MovementBehaviour getMovementBehaviour() {
        return null;
    }

    public Inventory getInventory() {
        return inventory;
    }


    @Override
    public CombatStats getCombatStats() {
        if (combatStats == null) {
            throw new IllegalStateException("Set Combatstats first!");
        }
        return combatStats;
    }

    @Override
    public void setCombatStats(CombatStats combatStats) {
        this.combatStats = combatStats;
    }
}
