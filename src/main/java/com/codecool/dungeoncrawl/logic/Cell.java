package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.mapItems.MapItem;
import com.codecool.dungeoncrawl.logic.playerItems.PlayerItem;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;




    private MapItem mapItem;
    private PlayerItem pLayerItem;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }
    public PlayerItem getItem() {
        return pLayerItem;
    }
    public MapItem getMapItem() {
        return mapItem;
    }
    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setPlayerItem(PlayerItem playerItem){
        this.pLayerItem = playerItem;
    }

    public void setMapItem(MapItem mapItem){
        this.mapItem = mapItem;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
