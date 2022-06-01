package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.Optional;

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

}
