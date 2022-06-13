package com.codecool.dungeoncrawl.logic.mapItems;

import com.codecool.dungeoncrawl.logic.Cell;

public class CaveWall extends MapItem{

    public CaveWall(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "caveWall";
    }
}
