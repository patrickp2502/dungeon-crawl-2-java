package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class Skeleton extends Asset implements Moveable, IsSolid, Fighter {

    private CombatStats combatStats;

    public Skeleton(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        combatStats = new CombatStats(10, 4);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public MovementBehaviour getMovementBehaviour() {
        return null;
    }

    @Override
    public CombatStats getCombatStats() {
        return combatStats;
    }

    @Override
    public void setCombatStats(CombatStats combatStats) {

    }
}
