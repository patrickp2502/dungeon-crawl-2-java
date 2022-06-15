package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.collections.Inventory;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;
import com.codecool.dungeoncrawl.logic.movementengine.behaviour.RandomMovementBehaviour;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

import java.util.List;

public class Player extends Asset implements IsSolid, Fighter, Moveable {

    private final Inventory inventory;
    private MovementBehaviour movementBehaviour;
    private CombatStats combatStats;

    private final int MAX_HEALTH;

    private final int ATTACK_POINTS;

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public int getATTACK_POINTS() {
        return ATTACK_POINTS;
    }

    public Player(String tileName, int xCoordinate, int yCoordinate) {
        super(tileName, xCoordinate, yCoordinate);
        movementBehaviour = new RandomMovementBehaviour();
        inventory = new Inventory();
        int health = 100;
        int attackPoints = 10;
        combatStats = new CombatStats(health, attackPoints);
        MAX_HEALTH = health;
        ATTACK_POINTS = attackPoints;
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void pickUpAllItemsPossible(List<Collectable> collectables) {
        collectables
                .stream()
                .filter(Collectable::isPickUpPossible)
                .forEach(inventory::addItem);
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


    /**
     * There will be no movement when player is in combat - player is in full control
     */
    @Override
    public void startCombatMovement() {
    }
    /**
     * There will be no movement when player is in combat - player is in full control
     */
    @Override
    public void stopCombatMovement() {
    }

    @Override
    public MovementBehaviour getMovementBehaviour() {
        return null;
    }

    @Override
    public void setMovementBehaviour(MovementBehaviour movementBehaviour) {

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
