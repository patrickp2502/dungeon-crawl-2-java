package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.Set;

public interface GameEventHandler {
    Set<Class<? extends GameEvent>> getGameEvents();

    void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses);

    public void handle(GameEvent event);


}
