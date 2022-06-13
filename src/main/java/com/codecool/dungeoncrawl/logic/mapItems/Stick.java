package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Stick extends MapItem implements Usable{
    public boolean isOpenedGate = false;

    public void open() {
        isOpenedGate = true;
    }
    public Stick(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        if(isOpenedGate) {
            return "openStick";
        } else {
            return "closeStick";
        }
    }

    @Override
    public void use(Player player) {
        open();
        player.setOpenStick(isOpenedGate);
    }
}
