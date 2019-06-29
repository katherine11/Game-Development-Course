package com.ksimeonova.shuffle.listener;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ksimeonova.shuffle.model.Enemy;
import com.ksimeonova.shuffle.model.Player;

public class Box2DContactListener implements ContactListener {

    private Player player;
    private Enemy enemy;
    private boolean colorMatch;

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

//        TODO: optimize:
        if(bodyA.getUserData() instanceof Player && bodyB.getUserData() instanceof Enemy){
            player = (Player) bodyA.getUserData();
            enemy = (Enemy) bodyB.getUserData();
        }

        if(bodyA.getUserData() instanceof Enemy && bodyB.getUserData() instanceof Player){
            enemy = (Enemy) bodyA.getUserData();
            player = (Player) bodyB.getUserData();
        }

        collide();

    }

    private void collide() {
        this.colorMatch = player.getColor().equals(enemy.getColor());

        if(colorMatch){
            player.moveOn();
        }
        else{
            player.die();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        if(colorMatch){
            contact.setEnabled(false);
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        if(colorMatch) {
            enemy.transform();
        }
    }
}
