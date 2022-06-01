package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.EventCollection;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;

import java.util.HashMap;
import java.util.List;

public record GameData(List<Asset> assetCollection, Player player) {

}
