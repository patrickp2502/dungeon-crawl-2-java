package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.logic.collectables.Collectable;

import java.util.List;

public class CollectableManager {

    public static void setPickUpToNotPossible(List<Collectable> collectables) {
        collectables
                .forEach(collectable -> collectable.setPickUpPossible(false));
    }
}
