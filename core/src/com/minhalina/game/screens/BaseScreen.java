package com.minhalina.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BaseScreen extends InputAdapter implements Screen {
    private final Stage mainStage;

    public BaseScreen(Game game) {
        mainStage = new Stage();
    }

    public Stage getStage() {
        return mainStage;
    }

    @Override
    public void show() {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.addProcessor(this);
        im.addProcessor(mainStage);
    }

    @Override
    public void render(float delta) {
        mainStage.act(delta);
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
    }

    public abstract void update(float dt);

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
        im.removeProcessor(this);
        im.removeProcessor(mainStage);
    }

    @Override
    public void dispose() {
        mainStage.dispose();
    }
}
