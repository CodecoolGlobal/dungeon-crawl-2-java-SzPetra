package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
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
