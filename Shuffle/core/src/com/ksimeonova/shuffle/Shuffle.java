package com.ksimeonova.shuffle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.ksimeonova.shuffle.screen.GameScreen;
import com.ksimeonova.shuffle.screen.MenuScreen;

public class Shuffle extends Game {

    public enum GAME_STATE{
        PLAY, MENU
    }

    public static float WIDTH = 4160;
    public static float HEIGHT = 2520;

    public static float WORLD_HEIGHT = 16;

    public GAME_STATE gameState = GAME_STATE.MENU;
    private Preferences preferences;
    private int highscore;

    @Override
    public void create() {
        this.highscore = 0;
        if(gameState == GAME_STATE.MENU){
            this.setScreen(new MenuScreen(this));
        }
        else{
            this.setScreen(new GameScreen(this));
        }
    }

    @Override
    public void render() {
        super.render();
        this.preferences = Gdx.app.getPreferences("highscorePreferences");
        if(preferences.contains("highscore")) {
            this.highscore = preferences.getInteger("highscore");
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public int getHighscore() {
        return highscore;
    }

    public void updateHighScore(int newHighScore){
        if(gameState == Shuffle.GAME_STATE.MENU){
            preferences.putInteger("highscore",newHighScore);
            preferences.flush();
        }
    }
}
