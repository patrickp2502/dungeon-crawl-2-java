package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventPlayerInputMove;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventRoundEnd;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.util.GameInformation;

import java.util.Set;

//TODO Question - Using static OK? Performance? Advantages? or throwing away? When we don t need the object?
//but what if we need to define interface? Why would we need to define an interface??
public class EventHandlerPlayerMove implements GameEventHandler {
    private int directionX;
    private int directionY;
    private Player player;
    private GameInformation gameInformation;

    private Set<Class<? extends GameEvent>> gameEventClasses;

    public EventHandlerPlayerMove(Set<Class<? extends GameEvent>> gameEventClasses) {
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

        EventPlayerInputMove eventPlayerInputMove = (EventPlayerInputMove) event;
        this.directionX = eventPlayerInputMove.directionX();
        this.directionY = eventPlayerInputMove.directionY();
        this.gameInformation = eventPlayerInputMove.gameInformation();
        player = gameInformation.getAssetCollection().getPlayer().get();
        movePlayer();
        EventEngine.getInstance().addEvent(new EventRoundEnd(gameInformation));
        EventEngine.getInstance().eventIsHandled(eventPlayerInputMove);
    }

    private void movePlayer() {
        int playerXCoordinate = player.getXCoordinate();
        int playerYCoordinate = player.getYCoordinate();
        int newCoordinateX = player.getXCoordinate() + directionX;
        int newCoordinateY = player.getYCoordinate() + directionY;
        PhysEngine physEngine = PhysEngine.getEngine();

        if (physEngine.tryToMove(player, newCoordinateX, newCoordinateY)) {
            player.setXCoordinate(playerXCoordinate + directionX);
            player.setYCoordinate(playerYCoordinate + directionY);
        }
    }
}
