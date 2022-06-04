package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.List;
import java.util.Set;

public interface GameEventHandler {
    void setGameEvents(Set<Class <? extends GameEvent>> gameEventClasses);
    Set<Class <? extends GameEvent>> getGameEvents();
    public void handle(GameEvent event);


}
