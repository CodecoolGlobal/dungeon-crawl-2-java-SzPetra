package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Exit extends MapItem implements Usable{

    private boolean isOpened = false;

    public void open() {
        isOpened = true;
        getCell().setType(CellType.FLOOR);
    }
    public Exit(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if(isOpened) {
            return "openExit";
        } else {
            return "exit";
        }
    }

    @Override
    public void use(Player player) {
        if (player.hasBlueKey()){
            player.setExitFirstMap(true);
            open();
        }
    }
}
