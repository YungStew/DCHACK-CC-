package com.hampton.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.GameScreen;
import com.hampton.game.utils.ActorUtils;

/**
 * Created by MichaelS on 2/10/2018.
 */

public class MainScreen extends GameScreen {

    private Actor title;
    private Actor start;


    @Override
    public void initialize() {

    }

    @Override
    public void createActors() {

     title= ActorUtils.createButtonFromText("YOU LOSE", new Color(1, 1, 1, 1));

    }

    @Override
    public void setInputForActors() {

    }

    @Override
    public void setActionsForActors() {

    }

    @Override
    protected void calledEveryFrame() {

    }
}
