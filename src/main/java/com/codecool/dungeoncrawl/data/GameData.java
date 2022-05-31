package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.EventCollection;
import com.codecool.dungeoncrawl.logic.eventengine.EventEngine;

import java.util.HashMap;

public record GameData(AssetCollection assetCollection, Player player) {

}
