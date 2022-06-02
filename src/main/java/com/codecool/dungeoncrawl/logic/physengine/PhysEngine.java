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


    public PhysEngine(GameData gameData, WorldInformation worldInformation) {
        boundaryMaxX = worldInformation.levelBoundaryMaxX();
        boundaryMaxY = worldInformation.levelBoundaryMaxY();
        boundaryMinX = worldInformation.levelBoundaryMaxY();
        boundaryMinY = worldInformation.levelBoundaryMaxY();
        assetCollection = gameData.assetCollection();


    }
    public void collisionCheck(int x, int y) {


        return;
    }



    public boolean isCollideable(Asset asset) {
        return true;
    }


    public boolean isInBoundry(int x, int y) {
        return x >= boundaryMinY &&
                y >= boundaryMaxY &&
                x <= boundaryMaxX &&
                y <= boundaryMaxY;
    }

}
