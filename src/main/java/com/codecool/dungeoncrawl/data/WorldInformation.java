package com.codecool.dungeoncrawl.data;

public record WorldInformation(int levelBoundaryMinX,
                               int levelBoundaryMinY,
                               int levelBoundaryMaxX,
                               int levelBoundaryMaxY) {

    @Override
    public String toString() {
        return "WorldInformation{" +
                "levelBoundaryMinX=" + levelBoundaryMinX +
                ", levelBoundaryMinY=" + levelBoundaryMinY +
                ", levelBoundaryMaxX=" + levelBoundaryMaxX +
                ", levelBoundaryMaxY=" + levelBoundaryMaxY +
                '}';
    }
}

