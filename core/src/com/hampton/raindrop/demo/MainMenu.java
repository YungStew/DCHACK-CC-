package com.hampton.raindrop.demo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * Created by turnerd on 10/13/17.
 */

public class MainMenu extends com.hampton.raindrop.GameScreen {
    Actor buttonFromText;
    private String nextScreenName;

    public MainMenu(String nextScreenName) {
        this.nextScreenName = nextScreenName;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void createActors() {
        buttonFromText = com.hampton.raindrop.utils.ActorUtils.createButtonFromText(
                "Start new game",
                new Color(1, 1, 1, 1));
        buttonFromText.setPosition(
                stage.getViewport().getScreenWidth()/2 - buttonFromText.getWidth()/2,
                stage.getViewport().getScreenHeight()/2 - buttonFromText.getHeight()/2);
        stage.addActor(buttonFromText);
    }

    @Override
    public void setInputForActors() {

    }

    @Override
    public void setActionsForActors() {
        buttonFromText.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gotoScreen(nextScreenName);
            }
        });
    }

    @Override
    protected void calledEveryFrame() {

    }
}
