package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventCombatStart;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventOnDeath;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.Set;

public class EventHandlerCombat implements GameEventHandler {
    private final GameData gameData;
    private Fighter attacker;
    private Fighter defender;
    Set<Class<? extends GameEvent>> gameEventClasses;


    public EventHandlerCombat(Set<Class<? extends GameEvent>> gameEventClasses, GameData gameData) {
        this.gameData = gameData;
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return gameEventClasses;
    }

    @Override
    public void handle(GameEvent event) {

        EventCombatStart eventCombatStart = (EventCombatStart) event;
        attacker = eventCombatStart.attackerAsset();
        defender = eventCombatStart.defenderAsset();

        fight();
    }

    private void fight() {
        int attackerAttackPoints = attacker.getCombatStats().getAttackPoints();
        int defenderAttackPoints = defender.getCombatStats().getAttackPoints();
        int attackerHealth = attacker.getCombatStats().getHealth();
        int defenderHealth = defender.getCombatStats().getHealth();

        defenderHealth -= attackerAttackPoints;
        if (defenderHealth <= 0) {
            EventEngine.getInstance().addEvent(new EventOnDeath((Asset) defender));

            return;
        }

        attackerHealth -= defenderAttackPoints;
        if (attackerHealth <= 0) {
            EventEngine.getInstance().addEvent(new EventOnDeath((Asset) attacker));
        }
    }
}
