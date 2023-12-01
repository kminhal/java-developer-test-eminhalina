package com.minhalina.game.entities;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.File;

public class Shield extends BaseActor {
    public Shield(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("entities" + File.separator + "shield.png");
    }
}