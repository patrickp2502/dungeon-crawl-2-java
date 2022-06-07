package com.codecool.dungeoncrawl.logic.collections;

import com.codecool.dungeoncrawl.logic.collectables.Collectable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
/*        return items
                .stream()
                .forEach(item -> String.format("%s", item.getClass()))
                .collect(Collections.toList().join());*/
    }
}
