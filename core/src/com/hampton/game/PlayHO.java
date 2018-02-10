package com.hampton.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.utils.ActorUtils;

/**
 * Created by MichaelS on 2/10/2018.
 */

public class PlayHO extends GameScreen {
    private float xMove;
    private float yMove;
    private float maxMove = 20;
    private Actor ball1;
    private Actor bar;
    private Actor cpuBar;

    @Override
    public void initialize() {

    }

    @Override
    public void createActors() {

        ball1 = ActorUtils.createActorFromImage("ball1.jpg");
        ball1.setSize(ball1.getWidth(), ball1.getHeight());
        ball1.setPosition(
                stage.getViewport().getScreenWidth()/2 - ball1.getWidth()/2,
                stage.getViewport().getScreenHeight()/3 - ball1.getHeight()/2);
        stage.addActor(ball1);

        bar = ActorUtils.createActorFromImage("bar.png");
        bar.setSize(bar.getWidth(), bar.getHeight());
        bar.setPosition(
                stage.getViewport().getScreenWidth()/2 - bar.getWidth()/2,
                60);
        stage.addActor(bar);

        cpuBar = ActorUtils.createActorFromImage("cpuBar.png");
        cpuBar.setSize(bar.getWidth(), bar.getHeight());
        cpuBar.setPosition(
                stage.getViewport().getScreenWidth()/2 - cpuBar.getWidth()/2,
                60);
        stage.addActor(cpuBar);

    }

    @Override
    public void setInputForActors() {
        ball1.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Stop any other actions
                ball1.clearActions();
                xMove = MathUtils.random(maxMove) - maxMove /2;
                yMove = MathUtils.random(maxMove) - maxMove /2;
                ball1.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        if (ball1.getX() + xMove < 0) {
                            xMove = -xMove;
                        }
                        if (ball1.getX() + ball1.getWidth() + xMove > stage.getViewport().getScreenWidth()) {
                            xMove = -xMove;
                        }
                        if (ball1.getY() + yMove < 0) {
                            ball1.clearActions();
                        }
                        if (ball1.getY() + ball1.getHeight() + yMove > stage.getViewport().getScreenHeight()) {
                            yMove = -yMove;
                        }
                        ball1.moveBy(xMove, yMove);
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public void setActionsForActors() {

    }

    @Override
    protected void calledEveryFrame() {
        if(Gdx.input.isTouched()) {
            // input.getY sets 0 as the top but actors use 0 for the bottom so we have to flip it
            Vector2 touchPoint = new Vector2(
                    Gdx.input.getX(),
                    stage.getViewport().getScreenHeight() - Gdx.input.getY());
            // Moves the bar
            bar.setPosition(touchPoint.x - bar.getWidth()/ 2, bar.getY());
        }

        if (ActorUtils.actorsCollided(bar,ball1)){
            yMove = Math.abs(yMove);
        }

        if(Gdx.input.isTouched()) {
            // input.getY sets 0 as the top but actors use 0 for the bottom so we have to flip it
            Vector2 touchPoint = new Vector2(
                    Gdx.input.getX(),
                    stage.getViewport().getScreenHeight() - Gdx.input.getY());
            // Moves the bar
            cpuBar.setPosition(touchPoint.x - cpuBar.getWidth()/ 2, cpuBar.getY());
        }

        if (ActorUtils.actorsCollided(cpuBar,ball1)){
            yMove = Math.abs(yMove);
        }

    }
}
