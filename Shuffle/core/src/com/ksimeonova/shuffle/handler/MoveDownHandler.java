package com.ksimeonova.shuffle.handler;

import com.ksimeonova.shuffle.model.Player;

public class MoveDownHandler implements Movable {

    private Player player;

    public MoveDownHandler(Player player){
        this.player = player;
    }

    @Override
    public void move() {
       player.move(Player.MOVING_FORCE_DOWN);
    }
}
