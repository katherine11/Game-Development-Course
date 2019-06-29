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
    public static final float MOVING_FORCE_UP = 150f;
    public static final float MOVING_FORCE_DOWN = 150f;

    public Player(Shuffle shuffle, World physicalWorld, float x, float y, float size, Texture texture, BodyDef.BodyType bodyType){
        super(shuffle, physicalWorld, x, y,size,size, texture, bodyType);
        super.body.setLinearVelocity(LINEAR_VELOCITY, 0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(isOutOfBoundaries()){
            this.die();
            System.out.println("Out of boundaries");
        }

    }

    private boolean isOutOfBoundaries(){
        boolean outOfBoundaries = false;

        if(getY() > Shuffle.WORLD_HEIGHT) {
            outOfBoundaries = true;
        }

        if(getY() < 0){
            outOfBoundaries = true;
        }

        return outOfBoundaries;
    }

    public void moveOn(){
        this.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));
    }

    public void move(float force){
        body.setLinearVelocity(body.getLinearVelocity().x,0);
        body.applyForceToCenter(0, force, true);
    }

    public void die(){
        shuffle.gameState = Shuffle.GAME_STATE.MENU;
        shuffle.setScreen(new MenuScreen(shuffle));
    }
}
