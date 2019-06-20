package com.ksimeonova.shuffle.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ksimeonova.shuffle.model.Enemy;
import com.ksimeonova.shuffle.model.Player;

public class Box2DContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();

//        TODO: optimize
        if (classA.equals("com.ksimeonova.shuffle.model.Player") && classB.equals("com.ksimeonova.shuffle.model.Enemy")) {
            Player player = (Player) (contact.getFixtureA().getBody().getUserData());
            Enemy enemy = (Enemy) contact.getFixtureB().getBody().getUserData();
            collide(player, enemy);
        } else if (classA.equals("com.ksimeonova.shuffle.model.Enemy") && classB.equals("com.ksimeonova.shuffle.model.Player")) {
            Player player = (Player) (contact.getFixtureB().getBody().getUserData());
            Enemy enemy = (Enemy) contact.getFixtureB().getBody().getUserData();
            collide(player, enemy);
        }
    }

    private void collide(Player player, Enemy enemy) {
        boolean colorMatch = player.getColor().equals(enemy.getColor());

        if(colorMatch){
            player.move();
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

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
