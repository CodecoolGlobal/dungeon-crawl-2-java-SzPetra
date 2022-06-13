package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Door extends MapItem implements Usable{

    private boolean isOpened = false;

    public void open() {
        isOpened = true;
        getCell().setType(CellType.FLOOR);
    }
    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if(isOpened) {
            return "openedGate";
        } else {
            return "door";
        }
    }

    @Override
    public void use(Player player) {
        if (player.hasRedKey()){
            open();
        }
    }
}
