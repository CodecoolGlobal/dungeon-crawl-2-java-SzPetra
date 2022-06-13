package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Water extends MapItem implements Usable {
    public Water(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "water";
    }

    @Override
    public void use(Player player) {
        if (player.hasWaterPotion()){
            this.getCell().setType(CellType.FLOOR);
        }

    }
}
