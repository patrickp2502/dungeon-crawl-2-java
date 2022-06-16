package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.collectables.Sword;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventOnDeath;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;
import com.codecool.dungeoncrawl.util.GameInformation;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class EventHandlerOnDeath implements GameEventHandler {
    private final GameInformation gameInformation;
    private final List<Collectable> collectables;
    private Set<Class<? extends GameEvent>> gameEventClasses;


    public EventHandlerOnDeath(Set<Class<? extends GameEvent>> gameEventClasses, GameInformation gameInformation) {
        this.gameEventClasses = gameEventClasses;
        this.gameInformation = gameInformation;
        collectables = gameInformation.getAssetCollection().getCollectables();
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
        EventOnDeath eventOnDeath = (EventOnDeath) event;
        Asset deadAsset = eventOnDeath.deadAsset();
        Random random = new Random();
        if (deadAsset instanceof Player) {
            Main.exit();
            return;
        }
        gameInformation.getAssetCollection().removeAsset(deadAsset);
        //TODO Factory for Items here
        Asset randomDrop = new Sword("sword", deadAsset.getXCoordinate(), deadAsset.getYCoordinate());
        gameInformation.getAssetCollection().addAsset(randomDrop);
    }
}
