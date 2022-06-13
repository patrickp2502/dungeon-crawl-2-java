package com.codecool.dungeoncrawl.logic.eventengine.combat;

public class CombatStats {
    private static final int STANDARD_ATTACK_POINT = 1;
    int health;
    int attackPoints;

    public void setHealth(int health) {
        this.health = health;
    }

    public void decreaseHealth(int amountDecreaseHealth) {
        if (health <= 0) {
            throw new IllegalStateException("Health already at zero!");
        }
        health -= amountDecreaseHealth;
    }


    public void addHealth(int additionalHealth) {
        health += additionalHealth;
    }


    int getHealth() {
        return health;
    }


    void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }


    void increaseAttackPoints(int pointsToIncrease) {
        attackPoints += pointsToIncrease;
    }


    void decreaseAttackPoints(int pointsToDecrease) {
        if (attackPoints - pointsToDecrease <= 0) {
            attackPoints = STANDARD_ATTACK_POINT;
        } else {
            attackPoints -= pointsToDecrease;
        }
    }


    int getAttackPoints() {
        if (attackPoints == 0) {
            throw new IllegalStateException("need to set AttacPoints!");
        }
        return attackPoints;
    }

}
