package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.mapItems.*;
import com.codecool.dungeoncrawl.logic.playerItems.Apple;
import com.codecool.dungeoncrawl.logic.playerItems.BlueKey;
import com.codecool.dungeoncrawl.logic.playerItems.RedKey;
import com.codecool.dungeoncrawl.logic.playerItems.Sword;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;

    public Cell[][] getCells() {
        return cells;
    }

    private Cell[][] cells;

    private Player player;



    private List<Monster> monsters = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }
    public String convertToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Cell[] cells: cells){
            for (Cell cell: cells){
                if (cell.getType() == CellType.EMPTY){
                    stringBuilder.append(" ");
                } else if (cell.getActor() != null ){
                    if (cell.getActor() instanceof Monster){
                        if(cell.getActor() instanceof Ghost) {
                            stringBuilder.append("w");
                        } else if(cell.getActor() instanceof Skeleton) {
                            stringBuilder.append("s");
                        } else {
                            stringBuilder.append("n");
                        }
                    } else {
                        stringBuilder.append("@");
                    }
                } else if(cell.getMapItem() != null) {
                    if(cell.getMapItem() instanceof Bridge) {
                        stringBuilder.append("h");
                    } else if(cell.getMapItem() instanceof CaveCornerDownLeft) {
                        stringBuilder.append("l");
                    } else if(cell.getMapItem() instanceof CaveCornerDownRight) {
                        stringBuilder.append("1");
                    } else if(cell.getMapItem() instanceof CaveCornerUpLeft) {
                        stringBuilder.append("b");
                    } else if(cell.getMapItem() instanceof CaveCornerUpRight) {
                        stringBuilder.append("u");
                    } else if(cell.getMapItem() instanceof CaveDown) {
                        stringBuilder.append("d");
                    } else if(cell.getMapItem() instanceof CaveUp) {
                        stringBuilder.append("c");
                    } else if(cell.getMapItem() instanceof CaveWall) {
                        stringBuilder.append("o");
                    } else if(cell.getMapItem() instanceof Door) {
                        stringBuilder.append("m");
                    } else if(cell.getMapItem() instanceof Exit) {
                        stringBuilder.append("6");
                    } else if(cell.getMapItem() instanceof Gate) {
                        stringBuilder.append("g");
                    } else if(cell.getMapItem() instanceof Path) {
                        stringBuilder.append("p");
                    } else if(cell.getMapItem() instanceof Stick) {
                        stringBuilder.append("0");
                    } else if(cell.getMapItem() instanceof Water) {
                        stringBuilder.append("v");
                    } else {
                        stringBuilder.append("+");
                    }
                } else if(cell.getItem() != null) {
                    if(cell.getItem() instanceof Apple) {
                        stringBuilder.append("8");
                    } else if(cell.getItem() instanceof BlueKey) {
                        stringBuilder.append("k");
                    } else if(cell.getItem() instanceof RedKey) {
                        stringBuilder.append("r");
                    } else if(cell.getItem() instanceof Sword) {
                        stringBuilder.append("t");
                    } else {
                        stringBuilder.append("q");
                    }
                } else if(cell.getType() == CellType.FLOOR) {
                    stringBuilder.append(".");
                } else {
                    stringBuilder.append("#");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public List<String> getMonsterStat() {
        List<String> result = new ArrayList<>();

        for(Monster monster: monsters) {
            String monsterStat = monster.getClass().getSimpleName() + ": " + monster.getHealth();
            result.add(monsterStat);
        }

        return result;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void addMonster(Monster monster){
        this.monsters.add(monster);
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveMonsters(){
        for(Monster monster: monsters){
            if (monster.isDead()) {
                monster.getCell().setActor(null);
                monsters.remove(monster);

            } else {
                int[] move = monster.getDirection(player);
                monster.move(move[0], move[1]);
            }
        }
    }
}
