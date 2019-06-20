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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameWorld {

    public static final int COLUMNS_NUMBER = 8;
    public static final String PLAYER_IMAGE_NAME = "box.jpg";
    public static final int PLAYER_POSITION = 5;
    public static final int PLAYER_SIZE = 1;
    public static final int ENEMY_POSITION = 10;
    public static final int ENEMIES_DISTANCE = 15;
    public static final List<Color> GAME_COLORS = Collections.unmodifiableList(Arrays.asList(Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK));

    private Shuffle shuffle;
    private World physicalWorld;
    private Player player;
    private Stage stage;
    private List<EnemyColumn> enemyColumns;
    private float worldWidth;

    public GameWorld(Shuffle shuffle){
        this.shuffle = shuffle;
        this.physicalWorld = new World(new Vector2(0,0),false);
        this.physicalWorld.setContactListener(new Box2DContactListener());

        Texture playerImage = new Texture(PLAYER_IMAGE_NAME);
        this.player = new Player(shuffle,physicalWorld, PLAYER_POSITION,Shuffle.WORLD_HEIGHT / 2 + 1, PLAYER_SIZE,playerImage);
        this.player.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));

        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = Shuffle.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,Shuffle.WORLD_HEIGHT));
        this.stage.addActor(player);

        this.initEnemies();
    }

    private void initEnemies(){
            enemyColumns = new ArrayList<EnemyColumn>(COLUMNS_NUMBER);
            EnemyColumn first = new EnemyColumn(shuffle,physicalWorld,stage, ENEMY_POSITION);
            enemyColumns.add(first);
            for(int i = 1; i < COLUMNS_NUMBER; i++){
                EnemyColumn enemyColumn = new EnemyColumn(shuffle,physicalWorld,stage,enemyColumns.get(i -1).getX() + ENEMIES_DISTANCE);
                enemyColumns.add(enemyColumn);
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
