package com.codecool.dungeoncrawl.logic.playerItems;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class PlayerItem implements Drawable {

    private Cell cell;

    public PlayerItem(Cell cell) {
        this.cell = cell;
        this.cell.setPlayerItem(this);
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }



}
