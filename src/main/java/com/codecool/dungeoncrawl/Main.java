package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main extends Application implements EventHandler<ActionEvent> {

    Stage secondaryStage = new Stage();
    Button saveButton;
    Button loadButton;
    GameMap map = MapLoader.loadMap("/map.txt");
    GameMap map2 = MapLoader.loadMap("/secondmap.txt");
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();

    private boolean secondMap = false;
    private GridPane ui = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);

        refreshMap(map);

        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void refreshUI(GameMap map) {
        ui.getChildren().clear();

        int rowNum = 2;

        Label inventoryLabel = new Label("INVENTORY");
        ui.add(inventoryLabel, 0, 0);

        Label playerHealth = new Label(String.format("Player health: %d", map.getPlayer().getHealth()));
        ui.add(playerHealth, 0, 1);

        List<String> playerItems = map.getPlayer().getPlayerItemAsString();
        for (String item : playerItems) {
            Label label = new Label(item);
            ui.add(label, 0, rowNum);
            rowNum++;
        }

        Label separator = new Label("MONSTER HEALTH");
        ui.add(separator, 0, rowNum++);

        List<String> monsterStats = map.getMonsterStat();
        for (String monster : monsterStats) {
            Label label = new Label(monster);
            ui.add(label, 0, rowNum);
            rowNum++;
        }
    }


    private void onKeyPressed(KeyEvent keyEvent) {
        GameMap actualMap;
        if (secondMap) {
            actualMap = map2;
        } else {
            actualMap = map;
        }

        switch (keyEvent.getCode()) {
            case UP:
                actualMap.getPlayer().move(0, -1);
                refreshMap(actualMap);
                break;
            case DOWN:
                actualMap.getPlayer().move(0, 1);
                refreshMap(actualMap);
                break;
            case LEFT:
                actualMap.getPlayer().move(-1, 0);
                refreshMap(actualMap);
                break;
            case RIGHT:
                actualMap.getPlayer().move(1, 0);
                refreshMap(actualMap);
                break;
            case S:
                saveWindow();
        }
    }

    private void refreshMap(GameMap map) {
        if (map.getPlayer().isExitFirstMap()) {
            secondMap = true;
        } else {
            map.moveMonsters();
            context.setFill(Color.BLACK);
            context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    Cell cell = map.getCell(x, y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x, y);

                    } else if (cell.getMapItem() != null) {
                        Tiles.drawTile(context, cell.getMapItem(), x, y);
                    } else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                }
            }
            if (map.getPlayer().isDead()) {
                System.exit(0);
            }
            refreshUI(map);

        }
    }

    public void saveWindow() {

        secondaryStage.setTitle("Save Game State");

        saveButton = new Button();
        saveButton.setText("SAVE");
        saveButton.setOnAction(this);

        /*loadButton = new Button();
        loadButton.setText("LOAD");
        loadButton.setOnAction(this);*/

        StackPane layout = new StackPane();
        layout.getChildren().add(saveButton);
        //layout.getChildren().add(loadButton);


        Scene scene = new Scene(layout, 300, 250);
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();
        try {
            gameDatabaseManager.setup();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(actionEvent.getSource() == saveButton) {
            if (map.getPlayer().isExitFirstMap()) {
                gameDatabaseManager.createNewSave(map2);
            }
            else {
                gameDatabaseManager.createNewSave(map);
            }

        }
        if(actionEvent.getSource() == loadButton) {
            System.out.println("this is the load button");
        }
    }
}