package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.AggressiveMovementBehaviour;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

public class Skeleton extends Asset implements Moveable, IsSolid, Fighter {


    private CombatStats combatStats;
    private MovementBehaviour movementBehaviour;
    private boolean movementStop;

    public Skeleton(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        combatStats = new CombatStats(15, 4);
        //movementBehaviour = new RandomMovementBehaviour();
        movementBehaviour = new AggressiveMovementBehaviour();

    }


    @Override
    public String getTileName() {
        return "skeleton";
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
    public boolean getCollisionMode() {
        return true;
    }

    @Override
    public void setCollisionMode(boolean isCollision) {

    }

    @Override
    public boolean getMovementStop() {
        return false;
    }

    @Override
    public void setMovementStop(boolean movementStop) {
        this.movementStop = movementStop;
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
}
