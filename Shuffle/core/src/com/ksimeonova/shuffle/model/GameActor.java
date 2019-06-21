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

public abstract class GameActor extends Image {

    public static final float DEFAULT_DENSITY = 0.5f;
    public static final float DEFAULT_FRICTION = 0.5f;
    public static final float DEFAULT_RESTITUTION = 0.5f;

    private World physicalWorld;
    protected Shuffle shuffle;
    protected Body body;

    public GameActor(Shuffle shuffle, World physicalWorld, float x, float y,float width,float height, Texture texture, BodyDef.BodyType bodyType) {
        super(texture);
        this.shuffle = shuffle;
        this.physicalWorld = physicalWorld;
        setPosition(x, y);
        setOrigin(x, y);
        setWidth(width);
        setHeight(height);
        initBody(bodyType);
    }

    protected void initBody(BodyDef.BodyType bodyType) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = bodyType;

        this.body = physicalWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2, getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = DEFAULT_DENSITY;
        fixtureDef.friction = DEFAULT_FRICTION;
        fixtureDef.restitution = DEFAULT_RESTITUTION;

        this.body.createFixture(fixtureDef);
        this.body.setUserData(this);

        bodyShape.dispose();
    }

    @Override
    public void act(float delta) {
        this.setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
    }

}
