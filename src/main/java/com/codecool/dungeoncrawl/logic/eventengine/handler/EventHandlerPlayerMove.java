package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
//TODO Question - Using static OK? Performance? Advantages? or throwing away? When we don t need the object?
//but what if we need to define interface? Why would we need to define an interface??
public class EventHandlerPlayerMove implements GameEventHandler {
    private EventPlayerInputMove eventPlayerInputMove;
    private int directionX;
    private int directionY;
    private Player player;
    private GameData gameData;

    @Override
    public void handle(GameEvent event) {
        eventPlayerInputMove = (EventPlayerInputMove) event;
        this.directionX = eventPlayerInputMove.directionX();
        this.directionY = eventPlayerInputMove.directionY();
        this.gameData = eventPlayerInputMove.gameData();
    }
}
