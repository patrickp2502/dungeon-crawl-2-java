package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.movement.Moveable;
import com.codecool.dungeoncrawl.logic.movement.AutomaticMovement;

public abstract class Actor implements Drawable {
    private int health = 10;
    private int x;
    private int y;


    public Actor(int x, int y) {
    }


    public int getHealth() {
        return health;
    }


    public int getX() {
        return cell.x();
    }

    public int getY() {
        return cell.y();
    }
}
