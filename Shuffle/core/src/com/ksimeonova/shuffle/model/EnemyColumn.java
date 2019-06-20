package com.ksimeonova.shuffle.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ksimeonova.shuffle.Shuffle;

import java.util.ArrayList;
import java.util.List;

public class EnemyColumn {

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

        for(int i = 0; i <= 5; i++){
                Texture texture = new Texture("Naruto.jpg");
                Enemy enemy = new Enemy(shuffle, physicalWorld, x, i * 4 + 2, 2, 4, texture);
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
