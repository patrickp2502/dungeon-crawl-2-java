package com.codecool.dungeoncrawl.logic.eventengine.events;

import com.codecool.dungeoncrawl.logic.eventengine.Fighter;

public record EventCombatStart(Fighter attackerAsset, Fighter defenderAsset) implements GameEvent {

}
