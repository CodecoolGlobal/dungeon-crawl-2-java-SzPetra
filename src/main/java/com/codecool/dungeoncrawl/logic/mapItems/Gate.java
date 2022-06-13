package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Gate extends MapItem implements Usable{

    private boolean isOpened = false;

    public void open() {
        isOpened = true;
        getCell().setType(CellType.FLOOR);
    }
    public Gate(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if(isOpened) {
            return "openedGate";
        } else {
            return "gate";
        }
    }

    @Override
    public void use(Player player) {
        if (player.hasBlueKey()){
            open();
        }
    }
}
