package com.codecool.dungeoncrawl.controls;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.collectables.Collectable;
import com.codecool.dungeoncrawl.logic.collections.Inventory;

public class ItemControl {

    public void collectItem(Player player, Collectable item) {
        Inventory inventory = player.getInventory();
        inventory.addItem(item);
        // print message "Adding <ItemName> to your Inventory was successful <PlayerName>"
    }

    public void  useItem(Player player, Collectable item) {
        if (player.getInventory().getItems().contains(item)) {
            //use Item
        }
    }
}
