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

public class Enemy extends Image {

    private Shuffle shuffle;
    private World physicalWorld;
    private Body body;

    public Enemy(Shuffle shuffle, World physicalWorld, float x, float y, float width, float height,
                 Texture texture) {
        super(texture);
        this.shuffle = shuffle;
        this.physicalWorld = physicalWorld;
        setPosition(x, y);
        setOrigin(x, y);
        setWidth(width);
        setHeight(height);

        initBody();
    }

//    TODO: extract to service
    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = physicalWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2, getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        body.setUserData(this);

        bodyShape.dispose();
    }

    @Override
    public void act(float delta) {
        this.setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }

    public void transform(){
        this.setColor(GAME_COLORS.get(new Random().nextInt(GAME_COLORS.size())));
    }

}


