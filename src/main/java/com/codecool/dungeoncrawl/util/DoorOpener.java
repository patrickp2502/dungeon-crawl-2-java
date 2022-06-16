package com.codecool.dungeoncrawl.util;

import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.scenery.DoorClosed;
import com.codecool.dungeoncrawl.logic.scenery.DoorOpened;

import java.util.List;

public class DoorOpener {
    public static void openDoor(DoorClosed door, List<Asset> allAssets) {
        DoorOpened openDoor = new DoorOpened("door open", door.getXCoordinate(), door.getYCoordinate());
        allAssets.remove(door);
        allAssets.add(openDoor);
    }

    public static void closeDoor(DoorOpened door, List<Asset> allAssets) {
        DoorClosed closedDoor = new DoorClosed("door closed", door.getXCoordinate(), door.getYCoordinate());
        allAssets.remove(door);
        allAssets.add(closedDoor);
    }
}
