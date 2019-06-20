package com.ksimeonova.shuffle.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ksimeonova.shuffle.Shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.ksimeonova.shuffle.game.GameWorld.GAME_COLORS;

public class EnemyColumn {

    public static final String ENEMY_IMAGE_NAME = "column.png";
    public static final float ENEMY_COLUMN_WIDTH = 0.5f;
    public static final int ENEMY_COLUMN_HEIGHT = 4;
    public static final int ENEMY_COLUMNS_NUMBER = 4;
    public static final int ENEMY_COLUMNS_COUNT = 5;

    private Shuffle shuffle;
    private World physicalWorld;
    private List<Enemy> enemies;
    private float x;
    private Stage stage;

    public EnemyColumn(Shuffle shuffle, World physicalWorld, Stage stage, float x) {
        this.shuffle = shuffle;
        this.physicalWorld = physicalWorld;
        this.x = x;
        this.stage = stage;
        initEnemies();
    }

    private void initEnemies() {
        this.enemies = new ArrayList<Enemy>();
        Collections.shuffle(GAME_COLORS);
        for (int i = 0; i < 4; i++) {
            Texture texture = new Texture(ENEMY_IMAGE_NAME);
            Enemy enemy = new Enemy(shuffle, physicalWorld, x, i * 4 + 2,
                    ENEMY_COLUMN_WIDTH, ENEMY_COLUMN_HEIGHT, texture, BodyDef.BodyType.StaticBody);
            Color color = GAME_COLORS.get(i);
            enemy.setColor(color);
            enemies.add(enemy);
        }

        for (Enemy enemy : enemies) {
            stage.addActor(enemy);
        }

    }

    public float getX() {
        return x;
    }
}
