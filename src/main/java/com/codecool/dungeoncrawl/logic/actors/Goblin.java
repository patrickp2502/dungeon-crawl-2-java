package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.AggressiveMovementBehaviour;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class Goblin extends Asset implements Fighter, Moveable, IsSolid {
    private final CombatStats combatStats;
    private MovementBehaviour movementBehaviour;

    public Goblin(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        this.combatStats = new CombatStats(20, 10);
        this.movementBehaviour = new AggressiveMovementBehaviour();
    }

    @Override
    public CombatStats getCombatStats() {
        return combatStats;
    }

    @Override
    public void setCombatStats(CombatStats combatStats) {

    }

    @Override
    public void startCombatMovement() {

    }

    @Override
    public void stopCombatMovement() {

    }

    @Override
    public MovementBehaviour getMovementBehaviour() {
        return movementBehaviour;
    }

    @Override
    public void setMovementBehaviour(MovementBehaviour movementBehaviour) {
        this.movementBehaviour = movementBehaviour;
    }

    @Override
    public void setCollisionMode(boolean isCollision) {

    }

    @Override
    public boolean getCollisionMode() {
        return false;
    }

    @Override
    public void setMovementStop(boolean movementStop) {

    }

    @Override
    public boolean getMovementStop() {
        return false;
    }
}
