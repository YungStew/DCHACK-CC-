package com.hampton.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    private Music musicSound;

    @Override
    public void initialize() {

    }

    @Override
    public void createActors() {

        ball1 = ActorUtils.createActorFromImage("Hockey Puck Black Power.png");
        ball1.setSize(125,125);
        ball1.setPosition(
                stage.getViewport().getScreenWidth()/2 - ball1.getWidth()/2,
                stage.getViewport().getScreenHeight()/3 - ball1.getHeight()/2);
        stage.addActor(ball1);

        bar = ActorUtils.createActorFromImage("Blue Striker.png");
        bar.setSize(bar.getWidth(), bar.getHeight());
        bar.setPosition(
                0, stage.getViewport().getScreenHeight()-bar.getHeight());
        stage.addActor(bar);


        cpuBar = ActorUtils.createActorFromImage("Red Striker.png");
        cpuBar.setSize(cpuBar.getWidth(), cpuBar.getHeight());
        cpuBar.setPosition(
                80,
                0);
        stage.addActor(cpuBar);

        musicSound = Gdx.audio.newMusic(Gdx.files.internal("AfrAmerSongs.mp3"));
        musicSound.setLooping(true);
        musicSound.play();
 
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
                        if (stage.getViewport().getScreenWidth() > ball1.getX() + ball1.getWidth() + xMove) {
                            xMove = -xMove;
                        }
                        if (ball1.getY() + yMove < 0) {
                            ball1.clearActions();
                        }
                        if (stage.getViewport().getScreenHeight() > ball1.getY() + ball1.getHeight() + yMove) {
                            yMove = -yMove;
                        }
                        ball1.moveBy(yMove, xMove);
                        return false;
                    }
                });
            }

            /* cpuBar.addListener(new ActorGestureListener() {
                @Override
                public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    // Stop any other actions
                    cpuBar.clearActions();
                    xMove = MathUtils.random(maxMove) - maxMove /2;
                    cpuBar.addAction(new Action() {
                        @Override
                        public boolean act(float delta) {
                            if (cpuBar.getX() + xMove < 0) {
                                xMove = -xMove;
                            }
                            if (cpuBar.getX() + ball1.getWidth() + xMove > stage.getViewport().getScreenWidth()) {
                                xMove = -xMove;
                            }

                            cpuBar.moveBy(xMove, 0);
                            return false;
                        }
                    });
                }*/
        });

    }

    @Override
    public void setActionsForActors() {

    }

    @Override
    protected void calledEveryFrame() {
       //for bar
        if(Gdx.input.isTouched()) {
            // input.getY sets 0 as the top but actors use 0 for the bottom so we have to flip it
            Vector2 touchPoint = new Vector2(
                    Gdx.input.getX(),
                    stage.getViewport().getScreenHeight() - Gdx.input.getY());
            // Moves the bar
            bar.setPosition(bar.getY(), touchPoint.x - bar.getWidth()/ 2);
        }

        if (ActorUtils.actorsCollided(bar,ball1)){
            yMove = Math.abs(yMove);
        }



    }

}
