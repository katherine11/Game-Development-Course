package com.ksimeonova.shuffle.handler;

import com.ksimeonova.shuffle.model.Player;

public class MoveUpHandler implements Movable {

    private Player player;

    public MoveUpHandler(Player player){
        this.player = player;
    }

    @Override
    public void move() {
        player.move(Player.MOVING_FORCE_UP);
    }
}
