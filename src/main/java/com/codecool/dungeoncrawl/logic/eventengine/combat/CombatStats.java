package com.codecool.dungeoncrawl.logic.eventengine.combat;

public class CombatStats {
    private static final int STANDARD_ATTACK_POINT = 1;

    int health;
    int attackPoints;

    public CombatStats(int health, int attackPoints) {
        this.health = health;
        this.attackPoints = attackPoints;
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

    public int getHealth() {
        return health;
    }


    public void increaseAttackPoints(int pointsToIncrease) {
        attackPoints += pointsToIncrease;
    }


    public void decreaseAttackPoints(int pointsToDecrease) {
        if (attackPoints - pointsToDecrease <= 0) {
            attackPoints = STANDARD_ATTACK_POINT;
        } else {
            attackPoints -= pointsToDecrease;
        }
    }


    public int getAttackPoints() {
        if (attackPoints == 0) {
            throw new IllegalStateException("need to set AttacPoints!");
        }
        return attackPoints;
    }

}
