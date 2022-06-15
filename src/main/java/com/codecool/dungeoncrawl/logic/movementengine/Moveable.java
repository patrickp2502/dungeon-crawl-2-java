package com.codecool.dungeoncrawl.logic.movementengine;

import com.codecool.dungeoncrawl.logic.movementengine.behaviour.MovementBehaviour;

public interface Moveable {

    MovementBehaviour getMovementBehaviour();
    void setMovementBehaviour(MovementBehaviour movementBehaviour);
    /**
     * setting if movement monitors collisions from physEngine -> if false, collision is ignored
     * you can go through walls
     */

    void setCollisionMode(boolean isCollision);
    boolean getCollisionMode();

    void setMovementStop(boolean movementStop);
    boolean getMovementStop();

}
