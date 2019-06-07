package com.ksimeonova.shuffle;

import com.badlogic.gdx.Game;
import com.ksimeonova.shuffle.screen.GameScreen;

public class Shuffle extends Game {

    public static float WIDTH = 4160;
    public static float HEIGHT = 2520;

    public static float WORLD_HEIGHT = 16;

    @Override
    public void create() {
        this.setScreen(new GameScreen(this));
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
