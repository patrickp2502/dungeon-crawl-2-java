package com.codecool.dungeoncrawl.logic.physengine;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.data.WorldInformation;


public class PhysEngine {
    private final int boundaryMaxX;
    private final int boundaryMaxY;
    private final int boundaryMinX;
    private final int boundaryMinY;
    WorldInformation worldInformation;
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

    public void collisionCheck(int x, int y) {

//        EventEngine.getInstance().addEvent(new EventAssetCollision(collidingAsset, getsCollidedAsset));
    }


    public boolean isCollideable(Asset asset) {
        return true;
    }


    public boolean isInBoundry(int x, int y) {
        return x >= boundaryMinX &&
                y >= boundaryMinY &&
                x <= boundaryMaxX &&
                y <= boundaryMaxY;
    }

}
