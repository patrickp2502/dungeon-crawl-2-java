package com.codecool.dungeoncrawl.logic.itemengine;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collections.Inventory;
import com.codecool.dungeoncrawl.logic.scenery.DoorClosed;
import com.codecool.dungeoncrawl.logic.scenery.DoorOpened;
import com.codecool.dungeoncrawl.logic.scenery.Scenery;

import java.util.List;

public class ItemEngine {

    private final List<Asset> assets;
    private final Player player;

    private final Inventory inventory;

    private final Scenery door;


    public ItemEngine(List<Asset> assets) {
        this.assets = assets;
        this.player = pickPlayer();
        this.inventory = player.getInventory();
        this.door = pickDoor();
    }

    private Player pickPlayer() {
        return (Player) assets
                .stream()
                .filter(asset -> asset instanceof Player)
                .findFirst()
                .get();
    }

    private Scenery pickDoor() {
        return (Scenery) assets
                .stream()
                .filter(asset -> asset instanceof DoorOpened || asset instanceof DoorClosed)
                .findFirst()
                .get();
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public Player getPlayer() {
        return player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Scenery getDoor() {
        return door;
    }
}
