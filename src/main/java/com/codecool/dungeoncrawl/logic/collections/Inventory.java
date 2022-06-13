package com.codecool.dungeoncrawl.logic.collections;

import com.codecool.dungeoncrawl.logic.collectables.Collectable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {
    private final List<Collectable> items = new ArrayList<>();

    public List<Collectable> getItems() {
        return items;
    }

    public void addItem(Collectable item) {
        items.add(item);
    }

    public void deleteItem(Collectable item) {
        items.remove(item);
    }

    @Override
    public String toString() {
        List<String> itemList = items
                .stream()
                .map(item -> String.format("%s", item.getClass())).toList();
        String finalString = String.join("\n", itemList);
        return finalString + "Should work";
    }
}
