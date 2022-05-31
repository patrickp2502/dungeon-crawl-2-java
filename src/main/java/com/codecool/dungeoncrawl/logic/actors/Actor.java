package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.movement.Moveable;
import com.codecool.dungeoncrawl.logic.movement.AutomaticMovement;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;
    private AutomaticMovement normalMovement;

    public Actor(Cell cell) {
        this.cell = cell;
    }


    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.x();
    }

    public int getY() {
        return cell.y();
    }
}
