package com.ksimeonova.shuffle.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ksimeonova.shuffle.Shuffle;

import java.util.Random;

import static com.ksimeonova.shuffle.game.GameWorld.GAME_COLORS;

public class Player extends Image {

    private Shuffle shuffle;
    private World physicalWorld;
    private Body body;

    public Player(Shuffle shuffle, World physicalWorld, float x, float y, float size, Texture texture){
        super(texture);
        this.shuffle = shuffle;
        this.physicalWorld = physicalWorld;
        setPosition(x, y);
        setSize(size, size);
        setOrigin(x, y);
        initBody();
    }

    private void initBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(),getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = physicalWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2,getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        body.setLinearVelocity(5f,0);
        body.setUserData(this);

        bodyShape.dispose();

    }

    @Override
    public void act(float delta) {
        this.setPosition(body.getPosition().x - getWidth() / 2,body.getPosition().y - getHeight() / 2);
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }

    public void move(){
        this.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));
    }

    public void die(){
        System.out.println("Die");
//        TODO: proceed to the menu screen
    }
}
