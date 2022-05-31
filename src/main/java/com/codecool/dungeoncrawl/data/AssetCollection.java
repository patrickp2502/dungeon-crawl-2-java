package com.codecool.dungeoncrawl.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds every Asset and its X and Y, Hält unsere Assets
 */
public record AssetCollection() {
    public static final List<Asset> assets = new ArrayList<Asset>();
}
