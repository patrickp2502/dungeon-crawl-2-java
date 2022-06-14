package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.data.Asset;

public record EventOnDeath(Asset deadAsset) implements GameEvent {

}
