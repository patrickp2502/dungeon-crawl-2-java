package com.codecool.dungeoncrawl.logic.eventengine;

import com.codecool.dungeoncrawl.data.AssetCollection;
import com.codecool.dungeoncrawl.data.GameData;
import com.codecool.dungeoncrawl.display.Display;
import com.codecool.dungeoncrawl.data.Asset;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.eventengine.events.*;
import com.codecool.dungeoncrawl.logic.eventengine.handler.*;
import com.codecool.dungeoncrawl.util.GameInformation;
import com.codecool.dungeoncrawl.util.GameManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * In the InitEventHandlers.class you can register new Events to the EvenHandlers and invoke the @gameEventHandlers
 * List, holding GameEventHandlerObjects.{@link #getGameEventHandlers()} method Access the GameEvenHandlers.
 */
public class InitEventHandlers {
    private List<GameEventHandler> gameEventHandlers;
    private GameData gameData;

    private final Display display;

    private final List<Label> labels;

    private final List<Button> buttons;

    private final Player player;

    private final List<Asset> assets;

    private final GameInformation gameInformation;

    public InitEventHandlers(Display display, List<Label> labels, List<Button> buttons, AssetCollection assetCollection,
                             GameData gameData, GameInformation gameInformation) {

        this.display = display;
        this.labels = labels;
        this.buttons = buttons;
        this.assets = assetCollection.getAssets();
        this.player = getPlayer(assets);
        //
        this.gameData = new GameData(assetCollection, player);
        this.gameInformation = gameInformation;
        gameEventHandlers = new ArrayList<>();


        //Register EventPlayerInputMove.class to EventHandlerPlayerMove
        Set<Class<? extends GameEvent>> playerMoveEvents = new HashSet<>();
        playerMoveEvents.add(EventPlayerInputMove.class);
        gameEventHandlers.add(new EventHandlerPlayerMove(playerMoveEvents));

        //Register EventStandingOn.class to EventHandlerStandingOn
        Set<Class<? extends GameEvent>> standingOnEvents = new HashSet<>();
        standingOnEvents.add(EventStandingOn.class);
        gameEventHandlers.add(new EventHandlerStandingOn(standingOnEvents, display, labels, buttons, player, assets));

        //Register EventAssetCollision.class to EventHandlerOnCollision
        Set<Class<? extends GameEvent>> onCollisionEvents = new HashSet<>();
        onCollisionEvents.add(EventAssetCollision.class);
        gameEventHandlers.add(new EventHandlerOnCollision(onCollisionEvents, gameData));

        //Register EventRoundEnd.class to EventHandlerRoundEnd
        Set<Class<? extends GameEvent>> endRoundEvents = new HashSet<>();
        endRoundEvents.add(EventRoundEnd.class);
        gameEventHandlers.add(new EventHandlerEndRound(endRoundEvents, display, labels, buttons, getPlayer(assets)));
        /*
        //Register playerHasKeyEvents to EventHandlerPlayerHasKey
        Set<Class<? extends GameEvent>> playerHasKeyEvents = new HashSet<>();
        playerHasKeyEvents.add(EventPlayerHasKey.class);
        ItemEngine itemEngine = new ItemEngine(assets);
        gameEventHandlers.add(new EventHandlerPlayerHasKey(assets, itemEngine.getDoor()));
*/
        //Register CombatEvents to EventHandlerCombat
        Set<Class<? extends GameEvent>> combatEvents = new HashSet<>();
        combatEvents.add(EventCombatStart.class);
        gameEventHandlers.add(new EventHandlerCombat(combatEvents, gameData));

        //Register CombatEvents to EventHandlerCombat
        Set<Class<? extends GameEvent>> onDeathEvents = new HashSet<>();
        onDeathEvents.add(EventOnDeath.class);
        gameEventHandlers.add(new EventHandlerOnDeath(onDeathEvents, gameData));

        //Register NextLevelEvents to EventHandlerNextLevel
        Set<Class<? extends GameEvent>> nextLevelEvents = new HashSet<>();
        nextLevelEvents.add(EventNextLevel.class);
        gameEventHandlers.add(new EventHandlerNextLevel(gameInformation, nextLevelEvents));
    }

    private Player getPlayer(List<Asset> assets) {
        return (Player) assets
                .stream()
                .filter(asset -> asset instanceof Player)
                .findFirst()
                .get();
    }

    public List<GameEventHandler> getGameEventHandlers() {
        return gameEventHandlers;
    }

}