package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bridge extends MapItem{
    public Bridge(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bridge";
    }
}
