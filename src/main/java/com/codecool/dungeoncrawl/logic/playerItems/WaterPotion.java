package com.codecool.dungeoncrawl.logic.playerItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class WaterPotion extends PlayerItem{
    public WaterPotion(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "waterPoison";
    }
}
