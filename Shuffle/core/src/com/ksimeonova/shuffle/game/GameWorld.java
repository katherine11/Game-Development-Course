package com.ksimeonova.shuffle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ksimeonova.shuffle.Shuffle;
import com.ksimeonova.shuffle.listener.Box2DContactListener;
import com.ksimeonova.shuffle.model.EnemyColumn;
import com.ksimeonova.shuffle.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private Shuffle shuffle;
    private World physicalWorld;
    private Player player;
    private Stage stage;
    private List<EnemyColumn> enemyColumns;
    private float worldWidth;

    public GameWorld(Shuffle shuffle){
        this.shuffle = shuffle;
        this.physicalWorld = new World(new Vector2(0,-9.8f),false);
        this.physicalWorld.setContactListener(new Box2DContactListener());

        Texture playerImage = new Texture("box.jpg");
        this.player = new Player(shuffle,physicalWorld, 5,Shuffle.WORLD_HEIGHT / 2,1,playerImage);
        this.player.setColor(Color.PINK);

        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = Shuffle.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,Shuffle.WORLD_HEIGHT));
        this.stage.addActor(player);

        this.initEnemies();
    }

    private void initEnemies(){
            enemyColumns = new ArrayList<EnemyColumn>(8);
            EnemyColumn first = new EnemyColumn(shuffle,physicalWorld,stage,10);
            enemyColumns.add(first);
            for(int i = 1; i < 8; i++){
                EnemyColumn enemyWall = new EnemyColumn(shuffle,physicalWorld,stage,enemyColumns.get(i -1).getX() + 15);
                enemyColumns.add(enemyWall);
            }
    }

    public void render(){
        this.stage.draw();
        physicalWorld.step(Gdx.graphics.getDeltaTime(),6,2);
    }

    public void update(){
        this.stage.act();
    }


}
