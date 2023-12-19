package com.minhalina.game.entities;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.io.File;

public class Laser extends BaseActor {
    public Laser(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("entities" + File.separator + "laser.png");
        addAction(Actions.delay(1));
        addAction(Actions.after(Actions.fadeOut(0.5f)));
        addAction(Actions.after(Actions.removeActor()));
        setSpeed(600);
        setMaxSpeed(600);
        setDeceleration(0);
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        move(dt);
    }
}