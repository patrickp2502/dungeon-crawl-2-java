package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.data.Asset;

public record EventAssetCollision(Asset collides, Asset getCollided) implements GameEvent {
}
