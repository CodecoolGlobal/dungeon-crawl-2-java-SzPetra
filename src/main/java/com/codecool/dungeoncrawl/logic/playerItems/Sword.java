package com.codecool.dungeoncrawl.logic.playerItems;
import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends PlayerItem {

    private int baseAttack = 5;
    public int getBaseAttack() {
        return baseAttack;
    }

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "sword";
    }
}
