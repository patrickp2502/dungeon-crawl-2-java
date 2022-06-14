package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.scenery.DoorClosed;
import com.codecool.dungeoncrawl.logic.scenery.DoorOpened;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;
import com.codecool.dungeoncrawl.util.DoorOpener;

import java.util.List;
import java.util.Set;

public class EventHandlerPlayerHasKey implements GameEventHandler{

    private final List<Asset> assets;

    private final Scenery door;

    private Set<Class <? extends GameEvent>> gameEventClasses;

    public EventHandlerPlayerHasKey(List<Asset> assets, Scenery door) {
        this.assets = assets;
        this.door = door;
    }

    @Override
    public void setGameEvents(Set<Class<? extends GameEvent>> gameEventClasses) {
        this.gameEventClasses = gameEventClasses;
    }

    @Override
    public Set<Class<? extends GameEvent>> getGameEvents() {
        return this.gameEventClasses;
    }

    @Override
    public void handle(GameEvent event) {
        switch (door) {
            case DoorOpened doorOpened -> DoorOpener.closeDoor(doorOpened, assets);
            case DoorClosed doorClosed -> DoorOpener.openDoor(doorClosed, assets);
            case null, default -> {
            }
        }
    }
}
