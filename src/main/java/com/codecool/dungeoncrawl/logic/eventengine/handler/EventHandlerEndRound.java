package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.List;
import java.util.Set;

public class EventHandlerEndRound implements GameEventHandler {
    private Set<Class <? extends GameEvent>> gameEventClasses;

    public EventHandlerEndRound(Set<Class <? extends GameEvent>> eventClassToRegister) {
        this.gameEventClasses = eventClassToRegister;
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
        Main.turn();
    }
}
