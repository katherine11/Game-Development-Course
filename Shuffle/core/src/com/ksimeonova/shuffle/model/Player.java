package com.ksimeonova.shuffle.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ksimeonova.shuffle.Shuffle;
import com.ksimeonova.shuffle.screen.MenuScreen;

import java.util.Random;

import static com.ksimeonova.shuffle.game.GameWorld.GAME_COLORS;

public class Player extends GameActor {

    public static final float LINEAR_VELOCITY = 5f;
    public static final float FORCE_Y = 150f;

    public Player(Shuffle shuffle, World physicalWorld, float x, float y, float size, Texture texture, BodyDef.BodyType bodyType){
        super(shuffle, physicalWorld, x, y,size,size, texture, bodyType);
        super.body.setLinearVelocity(LINEAR_VELOCITY, 0);
    }

    public void move(){
        this.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));
    }

    public void moveUp(){
        body.setLinearVelocity(body.getLinearVelocity().x,0);
        body.applyForceToCenter(0, FORCE_Y, true);
    }

    public void moveDown(){
        body.setLinearVelocity(body.getLinearVelocity().x,0);
        body.applyForceToCenter(0, -FORCE_Y, true);
    }

    public void die(){
        System.out.println("Die");

        shuffle.gameState = Shuffle.GAME_STATE.MENU;
        shuffle.setScreen(new MenuScreen(shuffle));
    }
}
