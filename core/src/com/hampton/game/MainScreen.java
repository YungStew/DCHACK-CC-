package com.hampton.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.utils.ActorUtils;

/**
 * Created by MichaelS on 2/10/2018.
 */

public class MainScreen extends GameScreen {

    private Actor frontPage;
    private Actor start;
    private String nextScreenName;
    private PlayHO base;


    public MainScreen(String nextScreenName, PlayHO base) {
        this.nextScreenName = nextScreenName;
        this.base=base;
    }


    @Override
    public void initialize() {

    }

    @Override
    public void createActors() {

      // background
        frontPage = ActorUtils.createActorFromImage("Main Cover Photo.png");
        frontPage.setSize(stage.getViewport().getScreenWidth(), stage.getViewport().getScreenHeight());
        stage.addActor(frontPage);

     //start button
     start=ActorUtils.createActorFromImage("Start Black Power Button.png");
     start.setSize(300,300);
     start.setPosition(400,500);
     stage.addActor(start);

    }

    @Override
    public void setInputForActors() {

    }

    @Override
    public void setActionsForActors() {

        start.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                base.background=ActorUtils.createActorFromImage("AfricanAmericanBackground.png");
                gotoScreen(nextScreenName);
            }
        });
    }

    @Override
    protected void calledEveryFrame() {

    }


}
