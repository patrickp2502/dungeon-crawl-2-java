package com.codecool.dungeoncrawl.logic.collections;

import com.codecool.dungeoncrawl.logic.collectables.Collectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        Map<String, Long> itemStrings = items
                .stream()
                .collect(Collectors.groupingBy(Object::toString, Collectors.counting()));
        StringBuilder finalString = new StringBuilder();
        itemStrings
                .forEach((key, value) -> finalString.append(key).append(": ").append(value).append("\n"));

        return finalString.toString();
    }
}
