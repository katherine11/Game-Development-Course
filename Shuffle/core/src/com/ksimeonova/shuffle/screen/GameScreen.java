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
import com.ksimeonova.shuffle.model.Player;

public class GameScreen implements Screen {

    private Shuffle shuffle;
    private Stage stage;
    private World physicalWorld;
    private Player player;
    private Texture playerImage;
    private float worldWidth;

    public GameScreen(Shuffle shuffle) {
        this.shuffle = shuffle;
    }

    @Override
    public void show() {
        playerImage = new Texture("Inner_Sakura.png");
        physicalWorld = new World(new Vector2(0, -0.5f), false);
        this.player = new Player(shuffle, physicalWorld, 5, Shuffle.WORLD_HEIGHT / 2, 5, playerImage);

        float ratio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        this.worldWidth = Shuffle.WORLD_HEIGHT / ratio;

        this.stage = new Stage(new StretchViewport(worldWidth, Shuffle.WORLD_HEIGHT));
        this.stage.addActor(player);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(153 / 255f, 102 / 255f, 1, 1);
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
    }
}
