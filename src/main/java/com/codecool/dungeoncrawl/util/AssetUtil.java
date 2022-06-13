package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.data.Asset;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssetUtil {

    public static List<Asset> getAssetByCoordinates(List<Asset> assets, int x, int y) {
        return assets
                .stream()
                .filter(asset -> asset.getXCoordinate() == x && asset.getYCoordinate() == y)
                .collect(Collectors.toList());
    }
}
