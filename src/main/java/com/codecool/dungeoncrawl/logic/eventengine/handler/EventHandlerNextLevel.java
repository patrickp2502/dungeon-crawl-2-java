package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.util.GameInformation;
import com.codecool.dungeoncrawl.util.GameManager;

import java.util.Set;

public class EventHandlerNextLevel implements GameEventHandler {

    private final GameInformation gameInformation;

    private Set<Class<? extends GameEvent>> gameEventClasses;

    public EventHandlerNextLevel(GameInformation gameInformation, Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameInformation = gameInformation;
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return gameEventClasses;
    }

    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {

    }

    @Override
    public void handle(GameEvent event) {
        gameInformation.setMap(GameManager.loadNextLevel(gameInformation));

    }
}
