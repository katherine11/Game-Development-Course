package com.ksimeonova.shuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ksimeonova.shuffle.Shuffle;
import com.ksimeonova.shuffle.listener.Box2DContactListener;
import com.ksimeonova.shuffle.model.Enemy;
import com.ksimeonova.shuffle.model.EnemyColumn;
import com.ksimeonova.shuffle.model.Player;

public class GameScreen implements Screen {

//    TODO: remove unused fields
    private Shuffle shuffle;
    private Stage stage;
    private World physicalWorld;
    private Player player;
    private Enemy enemy;
    private EnemyColumn column;
    private Texture playerImage;
    private Texture enemyImage;
    private float worldWidth;

    public GameScreen(Shuffle shuffle) {
        this.shuffle = shuffle;
    }

//    TODO: extract constants

    @Override
    public void show() {
        physicalWorld = new World(new Vector2(0, -0.5f), false);
        physicalWorld.setContactListener(new Box2DContactListener());

        playerImage = new Texture("Inner_Sakura.png");
        this.player = new Player(shuffle, physicalWorld, 5, Shuffle.WORLD_HEIGHT / 2, 3, playerImage);

        float ratio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        this.worldWidth = Shuffle.WORLD_HEIGHT / ratio;

        this.stage = new Stage(new StretchViewport(worldWidth, Shuffle.WORLD_HEIGHT));
        this.stage.addActor(player);

        this.column = new EnemyColumn(shuffle, physicalWorld, stage, 10);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255 / 255f, 204/ 255f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        physicalWorld.step(Gdx.graphics.getDeltaTime(), 6, 2);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        playerImage.dispose();
        enemyImage.dispose();
    }
}
