package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Actor implements Drawable {

    protected Cell cell;
    protected int health;
    protected int baseAttack;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        if(nextCell.getTileName().equals("floor") && nextCell.getActor() == null) {
            getCell().setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if(nextCell.getTileName().equals("floor") && nextCell.getActor() != null) {
            attack(nextCell.getActor());
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    protected abstract void attack(Actor enemy);

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
