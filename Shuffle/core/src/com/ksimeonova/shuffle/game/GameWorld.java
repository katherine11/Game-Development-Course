package com.ksimeonova.shuffle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ksimeonova.shuffle.Shuffle;
import com.ksimeonova.shuffle.listener.Box2DContactListener;
import com.ksimeonova.shuffle.model.EnemyColumn;
import com.ksimeonova.shuffle.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameWorld {

    public static final String PLAYER_IMAGE_NAME = "box.jpg";
    public static final int COLUMNS_NUMBER = 8;
    public static final int PLAYER_POSITION_X = 5;
    public static final int PLAYER_SIZE = 1;
    public static final int ENEMY_POSITION = 20;
    public static final int ENEMIES_DISTANCE = 15;
    public static final List<Color> GAME_COLORS = Arrays.asList(Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK);
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;
    public static final int CAMERA_MOVEMENT_SIZE = 5;

    private Shuffle shuffle;
    private World physicalWorld;
    private Player player;
    private Stage stage;
    private List<EnemyColumn> enemyColumns;
    private float worldWidth;

    public GameWorld(Shuffle shuffle){
        this.shuffle = shuffle;
        this.physicalWorld = new World(new Vector2(0, 0), false);
        this.physicalWorld.setContactListener(new Box2DContactListener());

        Texture playerImage = new Texture(PLAYER_IMAGE_NAME);
        this.player = new Player(shuffle, physicalWorld, PLAYER_POSITION_X, Shuffle.WORLD_HEIGHT / 2,
                PLAYER_SIZE,playerImage, BodyDef.BodyType.DynamicBody);
        this.player.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));

        float ratio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        this.worldWidth = Shuffle.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth, Shuffle.WORLD_HEIGHT));
        this.stage.addActor(player);

        this.initEnemies();
    }

    private void initEnemies(){
            enemyColumns = new ArrayList<>(COLUMNS_NUMBER);
            EnemyColumn first = new EnemyColumn(shuffle, physicalWorld,stage, ENEMY_POSITION);
            enemyColumns.add(first);
            for(int i = 1; i < COLUMNS_NUMBER; i++){
                EnemyColumn enemyColumn = new EnemyColumn(shuffle, physicalWorld,stage,enemyColumns.get(i -1).getX() + ENEMIES_DISTANCE);
                enemyColumns.add(enemyColumn);
            }
    }

    public void render(){
        this.stage.draw();
    }

    public void update() {
        this.stage.act();
        this.stage.getCamera().position.x = player.getX() + CAMERA_MOVEMENT_SIZE;
        physicalWorld.step(Gdx.graphics.getDeltaTime(), VELOCITY_ITERATIONS, POSITION_ITERATIONS);

//        TODO: implement handler methods
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.moveUp();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.moveDown();
        }
    }


}
