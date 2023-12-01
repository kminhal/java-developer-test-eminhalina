package com.minhalina.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.minhalina.game.screens.StartScreen;

import java.io.File;


public class AsteroidsGame extends Game {
    public static int WIDTH;
    public static int HEIGHT;
    public static LabelStyle labelStyleNormal;
    public static LabelStyle labelStyleLarge;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor(im);

        labelStyleNormal = new LabelStyle();
        labelStyleLarge = new LabelStyle();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
                Gdx.files.internal("font" + File.separator + "kenvector_future.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 24;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 2;
        fontParameters.borderColor = Color.BLACK;
        labelStyleNormal.font = fontGenerator.generateFont(fontParameters);
        fontParameters.size = 48;
        labelStyleLarge.font = fontGenerator.generateFont(fontParameters);
        fontGenerator.dispose();

        setScreen(new StartScreen(this));
    }
}
