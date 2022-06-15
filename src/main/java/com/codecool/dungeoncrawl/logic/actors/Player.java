package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.mapItems.MapItem;
import com.codecool.dungeoncrawl.logic.mapItems.Usable;
import com.codecool.dungeoncrawl.logic.playerItems.*;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    public String getName() {
        return name;
    }

    private String name = "Dani";

    private final int maxHealth = 10;
    public boolean isOpenStick() {
        return openStick;
    }

    private boolean openStick = false;

    public void setOpenStick(boolean openStick) {
        this.openStick = openStick;
    }

    public void setExitFirstMap(boolean exitFirstMap) {
        this.exitFirstMap = exitFirstMap;
    }

    private boolean exitFirstMap = false;

    List<PlayerItem> inventory = new ArrayList<>();

    private void pickUp(PlayerItem item) {
        if(item instanceof Consumable) {
            consume((Consumable) item);
        } else {
            inventory.add(item);
        }
        item.getCell().setPlayerItem(null);
    }

    private void consume(Consumable item) {
        if(item instanceof Apple) {
            this.health += item.consumeItem();
            if(health > maxHealth) {
                health = maxHealth;
            }
        }
    }

    private void interaction(MapItem mapItem) {
        if (mapItem instanceof Usable) {
            ((Usable) mapItem).use(this);
        }
    }

    public boolean hasWaterPotion() {
        for (PlayerItem playerItem : inventory) {
            if (playerItem instanceof WaterPotion) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRedKey() {
        for (PlayerItem playerItem : inventory) {
            if (playerItem instanceof RedKey) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBlueKey() {
        for (PlayerItem playerItem : inventory) {
            if (playerItem instanceof BlueKey) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = getCell().getNeighbor(dx, dy);
        PlayerItem playerItem = nextCell.getItem();
        if (playerItem != null) {
            pickUp(playerItem);
        }
        MapItem mapItem = nextCell.getMapItem();
        if (mapItem != null) {
            interaction(mapItem);
        }
        super.move(dx, dy);
    }

    public boolean isExitFirstMap() {
        return exitFirstMap;
    }

    private Sword getSword(){
        for (PlayerItem playerItem: inventory){
            if (playerItem instanceof Sword){
                return (Sword) playerItem;
            }
        }
        return null;
    }

    @Override
    protected void attack(Actor enemy) {
        int enemyHealth = enemy.getHealth();
        Sword sword = getSword();
        if (sword != null) {
            enemyHealth -= sword.getBaseAttack();
        } else {
            enemyHealth -= baseAttack;
        }
        enemy.setHealth(enemyHealth);
    }

    public List<String> getPlayerItemAsString() {
        List<String> result = new ArrayList<>();

        for (PlayerItem item : inventory) {
            result.add(item.getClass().getSimpleName());
        }

        return result;
    }

    public Player(Cell cell) {
        super(cell);
        baseAttack = 2;
        health = 10;
    }

    public String getTileName() {
        if (getSword() != null) {
            return "player";
        }
        return "justPlayer";
    }
}
