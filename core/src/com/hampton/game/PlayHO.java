package com.hampton.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.hampton.game.utils.ActorUtils;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by MichaelS on 2/10/2018.
 */

public class PlayHO extends GameScreen {

    private float xMove;
    private float yMove;
    private float maxMove = 20;
    private Actor ball1;
    private Actor redGoal;
    private Actor blueGoal;
    private Actor bar;
    private Actor background;
    private Actor cpuBar;
    private Music musicSound;
    private Label scoreLabel;
    private Label.LabelStyle scoreStyle;
    public int score=0;
    public int cpuScore=0;
    private boolean gameOn = false;

    @Override
    public void initialize() {
        
        musicSound = Gdx.audio.newMusic(Gdx.files.internal("AfrAmerSongs.mp3"));
        musicSound.setLooping(true);
        musicSound.play();

        gameOn=true;
        score=0;
        cpuScore=0;

        scoreStyle = new Label.LabelStyle(new BitmapFont(), new Color(1, 1, 1, 1));
        scoreStyle.font.getData().setScale(4);
        scoreLabel = new Label("0", scoreStyle);
        scoreLabel.setPosition(0, stage.getViewport().getScreenHeight() - scoreLabel.getHeight()-15);
        stage.addActor(scoreLabel);
    }

    @Override
    public void createActors() {

        background = ActorUtils.createActorFromImage("AfricanAmericanBackground.png");
        background.setSize(stage.getViewport().getScreenWidth(), stage.getViewport().getScreenHeight());
        stage.addActor(background);

        ball1 = ActorUtils.createActorFromImage("Hockey Puck Black Power.png");
        ball1.setSize(125,125);
        ball1.setPosition(
                stage.getViewport().getScreenWidth()/2 - ball1.getWidth()/2,
                stage.getViewport().getScreenHeight()/3 - ball1.getHeight()/2);
        stage.addActor(ball1);

        //player goal
        blueGoal=ActorUtils.createActorFromImage("Blue goal- UPDATED.png");
        blueGoal.setSize(blueGoal.getWidth(),blueGoal.getHeight());
        blueGoal.setPosition(500, stage.getViewport().getScreenHeight()-20);
        stage.addActor(blueGoal);

        //cpu goal
        redGoal=ActorUtils.createActorFromImage("Red Goal- UPDATED.png");
        redGoal.setSize(redGoal.getWidth(), redGoal.getHeight());
        redGoal.setPosition(500, 20);
        stage.addActor(redGoal);

        bar = ActorUtils.createActorFromImage("Blue Puck- UPDATED.png");
        bar.setSize(bar.getWidth(), bar.getHeight());
        bar.setPosition(
                0, 200);
        stage.addActor(bar);


        cpuBar = ActorUtils.createActorFromImage("Red Puck- UPDATED.png");
        cpuBar.setSize(cpuBar.getWidth(), cpuBar.getHeight());
        cpuBar.setPosition(
                80,
               stage.getViewport().getScreenHeight()-cpuBar.getHeight() );
        stage.addActor(cpuBar);

    }


   /* public void cpuBarMove(){
           cpuBar.addListener(new ActorGestureListener() {
                @Override
                public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    // Stop any other actions
                    cpuBar.clearActions();
                    xMove = MathUtils.random(maxMove) - maxMove /2;
                    cpuBar.addAction(new Action() {
                        public boolean act(float delta)

                        {
                            if (cpuBar.getX() + xMove < 0) {
                                xMove = -xMove;
                            }
                            if (cpuBar.getX() + cpuBar.getWidth() + xMove > stage.getViewport().getScreenWidth()) {
                                xMove = -xMove;
                            }

                            cpuBar.moveBy(xMove, 0);
                            return false;
                        }
                    });
                }
    }
    }*/


    @Override
    public void setInputForActors() {
        ball1.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Stop any other actions
                ball1.clearActions();
                xMove = MathUtils.random(maxMove) - maxMove /2;
                //yMove = MathUtils.random(maxMove) - maxMove /2;
                ball1.addAction(new Action() {
                    @Override
                    public boolean act(float delta) {
                        if (ball1.getX() + xMove < 0) {
                            xMove = -xMove;
                        }
                        if (ball1.getX() + ball1.getWidth() + xMove > stage.getViewport().getScreenWidth()) {
                            xMove = -xMove;
                        }

                        ball1.moveBy(xMove,stage.getViewport().getScreenHeight()-cpuBar.getHeight());
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
       //for bar
        if(Gdx.input.isTouched()) {
            // input.getY sets 0 as the top but actors use 0 for the bottom so we have to flip it
            Vector2 touchPoint = new Vector2(
                    Gdx.input.getX(),
                    stage.getViewport().getScreenHeight() - Gdx.input.getY());
            // Moves the bar
            bar.setPosition(touchPoint.x-bar.getWidth()/2, 0);
        }

        if (ActorUtils.actorsCollided(bar,ball1)){
            yMove = Math.abs(yMove);
        }

        if (ActorUtils.actorsCollided(cpuBar,ball1)){
            yMove = -1 * Math.abs(yMove);
        }


        if (ActorUtils.actorsCollided(blueGoal, ball1)) {
            ball1.setPosition(
                    stage.getViewport().getScreenWidth()/2 - ball1.getWidth()/2,
                    stage.getViewport().getScreenHeight()/3 - ball1.getHeight()/2);
                    score++;
        }

        if (ActorUtils.actorsCollided(redGoal, ball1)) {
            ball1.setPosition(
                    stage.getViewport().getScreenWidth()/2 - ball1.getWidth()/2,
                    stage.getViewport().getScreenHeight()/3 - ball1.getHeight()/2);
                    cpuScore++;
        }

        scoreLabel.setText(score + " : " + cpuScore);
        if(score == 5 || cpuScore == 5){
            //go back to main screen
            gameOn = false;
            gotoScreen("Menu");
        }

        }

    }

