package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.logic.eventengine.Fighter;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventCombatStart;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.Set;

public class EventHandlerCombat implements GameEventHandler {


    Set<Class<? extends GameEvent>> gameEventClasses;

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
        Fighter attacker = eventCombatStart.attackerAsset();
        Fighter defender = eventCombatStart.defenderAsset();

        fight();
    }

    private void fight() {



    }


}
