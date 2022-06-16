package com.codecool.dungeoncrawl.logic.movementengine;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.util.GameInformation;

import java.util.List;

public class MovementEngine {
    final GameInformation gameInformation;
    final PhysEngine physEngine;
    final EventEngine eventEngine;


    public MovementEngine(GameInformation gameInformation, PhysEngine physEngine, EventEngine eventEngine) {
        this.gameInformation = gameInformation;
        this.physEngine = physEngine;
        this.eventEngine = eventEngine;
    }


    public void moveAssets() {
        List<Moveable> moveables = gameInformation.getAssetCollection().getMovableWithoutPlayer();
        //TODO @Markus will I join HELL for this?
        moveables.forEach(moveable -> moveable.getMovementBehaviour().move((Asset & Moveable) moveable, physEngine, gameInformation));

    }


}
