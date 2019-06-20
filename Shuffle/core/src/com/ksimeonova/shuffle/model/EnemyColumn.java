package com.ksimeonova.shuffle.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ksimeonova.shuffle.Shuffle;

import java.util.ArrayList;
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

    public EnemyColumn(Shuffle shuffle, World physicalWorld, Stage stage, float x){
        this.shuffle = shuffle;
        this.physicalWorld = physicalWorld;
        this.x = x;
        this.stage = stage;
        initEnemies();
    }

    private void initEnemies(){
        this.enemies = new ArrayList<Enemy>();

        for(int i = 0; i <= ENEMY_COLUMNS_COUNT; i++){
                Texture texture = new Texture(ENEMY_IMAGE_NAME);
                Enemy enemy = new Enemy(shuffle, physicalWorld, x, i * ENEMY_COLUMNS_NUMBER + 2,
                        ENEMY_COLUMN_WIDTH, ENEMY_COLUMN_HEIGHT, texture);
                enemy.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));
                enemies.add(enemy);
        }

        for(Enemy column: enemies){
            stage.addActor(column);
        }

    }

    public float getX() {
        return x;
    }
}
