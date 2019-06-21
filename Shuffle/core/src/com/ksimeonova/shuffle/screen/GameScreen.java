package com.ksimeonova.shuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ksimeonova.shuffle.Shuffle;
import com.ksimeonova.shuffle.game.GameWorld;
import com.ksimeonova.shuffle.listener.Box2DContactListener;

public class GameScreen implements Screen {

    private Shuffle shuffle;
    private OrthographicCamera camera;
    private World physicalWorld;
    private GameWorld gameWorld;

    public GameScreen(Shuffle shuffle) {
        this.shuffle = shuffle;
    }

//    TODO: extract constants

    @Override
    public void show() {
        physicalWorld = new World(new Vector2(0, -0.5f), false);
        physicalWorld.setContactListener(new Box2DContactListener());

        gameWorld = new GameWorld(shuffle);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Shuffle.WIDTH, Shuffle.HEIGHT);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0 / 255f, 20 / 255f, 40/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        physicalWorld.step(Gdx.graphics.getDeltaTime(), GameWorld.VELOCITY_ITERATIONS, GameWorld.POSITION_ITERATIONS);
        gameWorld.render();
        gameWorld.update();

        camera.update();
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

    }
}
