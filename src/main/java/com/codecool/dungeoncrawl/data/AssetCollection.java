package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Holds every Asset and its X and Y, Hält unsere Assets
 */
public class AssetCollection {
    private final ArrayList<Asset> assets = new ArrayList<>();

    public ArrayList<Asset> getAssets() {
        return assets;
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public Optional<Player> getPlayer() {
        return assets
                .stream()
                .filter(asset -> asset instanceof Player)
                .map(asset -> (Player) asset)
                .findAny();
    }


    public List<Asset> getAssetByCoordinates(int x, int y) {
        return assets
                .stream()
                .filter(asset -> asset.getXCoordinate() == x && asset.getYCoordinate() == y)
                .collect(Collectors.toList());
    }

    public List<Asset> getMovableAssets() {
        return assets
                .stream()
                .filter(asset -> asset instanceof Moveable)
                .collect(Collectors.toList());
    }




    public <T> List<T> separate(Class<T> className) {
        return assets
                .stream()
                .filter(element -> element.getClass().isAssignableFrom(className))
                .map(asset -> (T) asset)
                .collect(Collectors.toList());
    }

    public List<Moveable> getMovables() {
        List<Moveable> returnList  = new ArrayList<>();
        for (Asset asset : assets) {
            if ( asset instanceof Moveable) {
                returnList.add((Moveable) asset);
            }
        }
        return returnList;
    }

    public List<Scenery> getScenery() {
        List<Scenery> returnList  = new ArrayList<>();
        for (Asset asset : assets) {
            if ( asset instanceof Scenery) {
                returnList.add((Scenery) asset);
            }
        }
        return returnList;
    }

}
