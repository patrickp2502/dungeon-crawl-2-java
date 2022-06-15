package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;

public interface Fighter {
    CombatStats getCombatStats();
    void setCombatStats(CombatStats combatStats);

    /**
     * method is called when combat starts - so changing of e.g.
     * movement is possible
     */
    void startCombatMovement();
    void stopCombatMovement();

}
