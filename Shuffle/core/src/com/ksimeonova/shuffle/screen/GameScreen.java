package com.ksimeonova.shuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ksimeonova.shuffle.Shuffle;

public class GameScreen implements Screen {

    private Shuffle shuffle;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture img;

    public GameScreen(Shuffle shuffle){
        this.shuffle = shuffle;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Shuffle.WIDTH, Shuffle.HEIGHT);
        camera.update();

        img = new Texture("Inner_Sakura.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(153/255f, 102/255f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
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
        batch.dispose();
        img.dispose();
    }
}
