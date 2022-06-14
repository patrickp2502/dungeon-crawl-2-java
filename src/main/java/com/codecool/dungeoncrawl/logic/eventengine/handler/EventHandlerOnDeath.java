package com.codecool.dungeoncrawl.logic.eventengine.handler;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.collectables.Sword;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventOnDeath;
import com.codecool.dungeoncrawl.logic.eventengine.events.GameEvent;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class EventHandlerOnDeath implements GameEventHandler {
    private Set<Class <? extends GameEvent>> gameEventClasses;
    private final GameData gameData;
    private final List<Collectable> collectables;


    public EventHandlerOnDeath(Set<Class<? extends GameEvent>> gameEventClasses, GameData gameData) {
        this.gameEventClasses = gameEventClasses;
        this.gameData = gameData;
        collectables = gameData.assetCollection().getCollectables();
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
        EventOnDeath eventOnDeath = (EventOnDeath) event;
        Asset deadAsset = eventOnDeath.deadAsset();
        Random random = new Random();
        System.out.println("gameData.assetCollection().getAssets() = " + gameData.assetCollection().getMovableAssets());
        gameData.assetCollection().removeAsset(deadAsset);
        //TODO Factory for Items here
        //Asset randomDrop = new Sword("sword", deadCoordinateX, deadCoordinateY);
        //gameData.assetCollection().addAsset(randomDrop);
    }
}
