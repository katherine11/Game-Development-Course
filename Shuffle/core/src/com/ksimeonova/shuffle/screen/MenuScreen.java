package com.ksimeonova.shuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.ksimeonova.shuffle.Shuffle;

public class MenuScreen implements Screen {

    public static final String MENU_BACKGROUND_IMAGE_NAME = "menu_background.jpg";
    public static final String MENU_HEADING = "Shuffle";
    public static final String FONT_TITLE = "font.ttf";

    private Shuffle shuffle;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture menuBackground;
    private BitmapFont font;

    public MenuScreen(Shuffle shuffle){
        this.shuffle = shuffle;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,Shuffle.WIDTH,Shuffle.HEIGHT);
        this.camera.update();
        this.menuBackground = new Texture(MENU_BACKGROUND_IMAGE_NAME);
        this.initFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        this.batch.begin();
        this.batch.draw(menuBackground, 0, 0, Shuffle.WIDTH, Shuffle.HEIGHT);
        drawHeading();
        drawHighScore(shuffle.getHighscore(),batch);
        this.batch.end();

        if(Gdx.input.justTouched()){
            shuffle.gameState = Shuffle.GAME_STATE.PLAY;
            shuffle.setScreen(new GameScreen(shuffle));
        }
    }

    private void initFont(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_TITLE));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 150;
        this.font = generator.generateFont(parameter);
        generator.dispose();

        font.setColor(0f, 0f, 0f, 1f);
    }

    private void drawHeading(){
        font.setColor(new Color(0/255f, 51/255f, 102/255f, 1));
        GlyphLayout layout = new GlyphLayout(font, MENU_HEADING);
        float layoutWidth = layout.width;
        font.draw(batch, layout, camera.position.x - layoutWidth/2, Shuffle.HEIGHT/2);
    }

    private void drawHighScore(int highScore, SpriteBatch batch){
        font.setColor(new Color(0/255f, 51/255f, 102/255f, 1));
        GlyphLayout layout = new GlyphLayout(font, "High score: " + highScore);
        float layoutWidth = layout.width;
        font.draw(batch, layout, camera.position.x - layoutWidth/2, Shuffle.HEIGHT/2 - 350);
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
        this.batch.dispose();
    }
}
