package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;


public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameDao;

    private Date date;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameDao = new GameStateDaoJdbc(dataSource);
    }

    private PlayerModel savePlayer(Player player) {
        PlayerModel model = new PlayerModel(player);
        playerDao.add(model);

        return model;
    }

    public void saveGameState(GameState gameState) {
        gameDao.add(gameState);
    }

    public void saveGameStateOnPreviousSave(GameState currentGameState) { gameDao.update(currentGameState); }

    public void createNewSave(GameMap map) {
        Player player = map.getPlayer();
        PlayerModel playerModel = savePlayer(player);
        LocalTime localTime = LocalTime.now();
        Date saveTime = Date.valueOf(String.valueOf(localTime));
        GameState gameState = new GameState("map.txt",saveTime, playerModel);
    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
