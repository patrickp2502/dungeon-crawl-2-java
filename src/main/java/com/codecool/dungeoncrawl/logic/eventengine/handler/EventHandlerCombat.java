package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventCombatStart;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventOnDeath;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.util.GameInformation;

import java.util.Set;

public class EventHandlerCombat implements GameEventHandler {
    private final GameInformation gameInformation;
    Set<Class<? extends GameEvent>> gameEventClasses;
    private Fighter attacker;
    private Fighter defender;


    public EventHandlerCombat(Set<Class<? extends GameEvent>> gameEventClasses, GameInformation gameInformation) {
        this.gameInformation = gameInformation;
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return gameEventClasses;
    }

    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
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
        defender.getCombatStats().decreaseHealth(attackerAttackPoints);
        if (defenderHealth <= 0) {
            EventEngine.getInstance().addEvent(new EventOnDeath((Asset) defender));

            return;
        }

        attackerHealth -= defenderAttackPoints;
        attacker.getCombatStats().decreaseHealth(defenderAttackPoints);
        if (attackerHealth <= 0) {
            EventEngine.getInstance().addEvent(new EventOnDeath((Asset) attacker));
        }

    }
}
