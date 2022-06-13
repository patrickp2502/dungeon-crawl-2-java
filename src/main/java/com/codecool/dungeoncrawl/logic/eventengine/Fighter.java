package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.logic.eventengine.combat.CombatStats;

public interface Fighter {
    CombatStats getCombatStats();
    void setCombatStats(CombatStats combatStats);

}
