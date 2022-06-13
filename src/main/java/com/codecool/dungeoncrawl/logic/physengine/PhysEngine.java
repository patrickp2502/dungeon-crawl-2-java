package com.codecool.dungeoncrawl.logic.physengine;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.data.WorldInformation;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventAssetCollision;
import com.codecool.dungeoncrawl.logic.eventengine.events.EventStandingOn;
import com.codecool.dungeoncrawl.logic.physengine.assetPhysics.IsSolid;

import java.util.List;


public class PhysEngine {
    private final int boundaryMaxX;
    private final int boundaryMaxY;
    private final int boundaryMinX;
    private final int boundaryMinY;
    AssetCollection assetCollection;
    private static PhysEngine physEngineInstance = null;


    private PhysEngine(GameData gameData, WorldInformation worldInformation) {
        boundaryMinX = worldInformation.levelBoundaryMinX();
        boundaryMinY = worldInformation.levelBoundaryMinY();
        boundaryMaxX = worldInformation.levelBoundaryMaxX();
        boundaryMaxY = worldInformation.levelBoundaryMaxY();
        assetCollection = gameData.assetCollection();
    }

    public static void setPhysEngine(GameData gameData, WorldInformation worldInformation) {
        physEngineInstance = new PhysEngine(gameData, worldInformation);
    }

    public static PhysEngine getEngine() {
        return physEngineInstance;
    }

    public boolean tryToMove(Asset movingAsset, int x, int y) {
        return isInBoundry(x,y) && !isCollision(movingAsset, x, y);
    }




    public boolean isCollision(Asset movingAsset, int x, int y){
        List<Asset> getsCollidedAssets = assetCollection.getAssetByCoordinates(x, y);
        if (getsCollidedAssets.isEmpty()) {
            return false;
        }
        for (Asset asset : getsCollidedAssets) {
            if (asset instanceof IsSolid) {
                EventEngine.getInstance().addEvent(new EventAssetCollision(movingAsset, asset));
                return true;
            } else if (asset instanceof Collectable) {
                EventEngine.getInstance().addEvent(new EventStandingOn(asset));
            }
        }
        return false;

    }

    public boolean isCollideable(Asset asset) {
        return true;
    }


    private boolean isInBoundry(int x, int y) {
        return x >= boundaryMinX &&
                y >= boundaryMinY &&
                x <= boundaryMaxX &&
                y <= boundaryMaxY;
    }

}
