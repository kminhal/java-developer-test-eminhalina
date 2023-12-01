package com.minhalina.game.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.minhalina.game.AsteroidsGame;

import java.io.File;

public class Meteor extends BaseActor {

    public Meteor(float x, float y, Stage s) {
        super(x, y, s);
        int index = MathUtils.random(1, 11);
        loadTexture("entities" + File.separator + "meteor" + File.separator + "meteor" + index + ".png");
        float random = MathUtils.random(20);
        addAction(Actions.forever(Actions.rotateBy(30 + random, 1f)));
        setSpeed(30 + random);
        setMaxSpeed(30 + random);
        setDeceleration(0);
        setMotionAngle(MathUtils.random(360));
        addAction(Actions.after(Actions.fadeIn(5f)));
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        move(dt);
        wrapAroundWorld(AsteroidsGame.WIDTH, AsteroidsGame.HEIGHT);
    }
}
