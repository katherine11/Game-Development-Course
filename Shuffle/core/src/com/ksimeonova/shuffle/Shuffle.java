package com.ksimeonova.shuffle;

import com.badlogic.gdx.Game;
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

    @Override
    public void create() {
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
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
