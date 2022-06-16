package com.codecool.dungeoncrawl.logic.movementengine.behaviour;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.movementengine.Direction;
import com.codecool.dungeoncrawl.logic.movementengine.Moveable;
import com.codecool.dungeoncrawl.logic.physengine.PhysEngine;
import com.codecool.dungeoncrawl.util.GameInformation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AggressiveMovementBehaviour implements MovementBehaviour {

    private HelperCoordinate currentCoordinate;
    private HelperCoordinate playerCoordinate;

    @Override
    public <T extends Asset & Moveable> void move(T moveableAsset, PhysEngine physEngine, GameInformation gameInformation) {
        if (moveableAsset.getMovementStop()) {
            return;
        }
        this.currentCoordinate = new HelperCoordinate(moveableAsset.getXCoordinate(),
                moveableAsset.getYCoordinate());
        this.playerCoordinate = new HelperCoordinate(gameInformation.getAssetCollection().getPlayer().get().getXCoordinate(),
                gameInformation.getAssetCollection().getPlayer().get().getYCoordinate());

        List<HelperCoordinate> possibleCoordinates = createPossibleCoordinates();
        for (HelperCoordinate coordinate : possibleCoordinates) {
            if (physEngine.tryToMove(moveableAsset, coordinate.x, coordinate.y)) {
                moveableAsset.setXCoordinate(coordinate.x);
                moveableAsset.setYCoordinate(coordinate.y);
                return;
            }
        }


    }

    private List<HelperCoordinate> createPossibleCoordinates() {
        List<HelperCoordinate> possibleCoordinates = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            possibleCoordinates.add(
                    new HelperCoordinate(currentCoordinate.x + direction.getDirectionX(),
                            currentCoordinate.y + direction.getDirectionY()));
        }
        return sortCoordinates(possibleCoordinates);
    }

    private List<HelperCoordinate> sortCoordinates(List<HelperCoordinate> possibleCoordinates) {
        return possibleCoordinates.stream()
                .sorted(Comparator.comparingInt(coordinate -> coordinate.getDistance(playerCoordinate)))
                .collect(Collectors.toList());

    }

    private class HelperCoordinate {
        int x;
        int y;

        public HelperCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance(HelperCoordinate fromCoordinate) {
            return Math.abs(fromCoordinate.x - x) + Math.abs(fromCoordinate.y - y);
        }

        @Override
        public String toString() {
            return "Coord {" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
