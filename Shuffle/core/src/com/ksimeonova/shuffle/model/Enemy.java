package com.ksimeonova.shuffle.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ksimeonova.shuffle.Shuffle;

import java.util.Random;

import static com.ksimeonova.shuffle.game.GameWorld.GAME_COLORS;

public class Enemy extends GameActor {

    public Enemy(Shuffle shuffle, World physicalWorld, float x, float y, float width, float height,
                 Texture texture, BodyDef.BodyType bodyType) {
        super(shuffle, physicalWorld, x, y,width,height, texture, bodyType);
    }

    public void transform(){
        this.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));
    }

}


