package com.ksimeonova.shuffle.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ksimeonova.shuffle.constants.MoveType;
import com.ksimeonova.shuffle.handler.Movable;
import com.ksimeonova.shuffle.handler.MoveDownHandler;
import com.ksimeonova.shuffle.handler.MoveRightHandler;
import com.ksimeonova.shuffle.handler.MoveUpHandler;
import com.ksimeonova.shuffle.model.Player;

public class MoveService {

    public MoveService(){

    }

    public Movable getMoveHandler(Player player){
        MoveType moveType = getMoveType();
        Movable moveHandler = new MoveRightHandler(player);

        if (moveType == MoveType.UP) {
            moveHandler = new MoveUpHandler(player);
        }

        if (moveType == MoveType.DOWN) {
            moveHandler = new MoveDownHandler(player);
        }

        return moveHandler;
    }

//    TODO: Refactor
    private MoveType getMoveType(){
        boolean positionUp = false;
        boolean positionDown = false;

        if(Gdx.input.justTouched()){
            if(Gdx.input.getX() > Gdx.graphics.getWidth()/2){
                positionUp = true;
            }
            else{
                positionDown = true;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP) || positionUp){
            return MoveType.UP;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || positionDown){
            return MoveType.DOWN;
        }

        return MoveType.NONE;
    }
}
