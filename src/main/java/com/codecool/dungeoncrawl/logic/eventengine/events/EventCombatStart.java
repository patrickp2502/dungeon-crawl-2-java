package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.data.Asset;

public record EventCombatStart(Asset attackerAsset, Asset defenderAsset) implements GameEvent {

}
