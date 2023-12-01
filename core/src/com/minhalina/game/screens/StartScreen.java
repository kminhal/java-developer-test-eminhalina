package com.minhalina.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.minhalina.game.AsteroidsGame;
import com.minhalina.game.entities.BaseActor;
import com.minhalina.game.entities.Meteor;

public class StartScreen extends BaseScreen {
    private final Game game;

    public StartScreen(Game game) {
        super(game);
        this.game = game;
        Preferences prefs = Gdx.app.getPreferences("game.properties");

        BaseActor space = new BaseActor(0, 0, getStage());
        space.loadTexture("bg.png");
        space.setSize(AsteroidsGame.WIDTH, AsteroidsGame.HEIGHT);

        initMeteors();

        Label title = new Label("Asteroids", AsteroidsGame.labelStyleLarge);
        title.setPosition(
                AsteroidsGame.WIDTH / 2f - title.getWidth() / 2,
                AsteroidsGame.HEIGHT / 2f + title.getHeight() / 2);
        getStage().addActor(title);

        Label score = new Label("Highscore: " + prefs.getInteger("score"), AsteroidsGame.labelStyleNormal);
        score.setPosition(
                AsteroidsGame.WIDTH / 2f - score.getWidth() / 2,
                title.getY() - score.getHeight() - title.getHeight());
        getStage().addActor(score);

        Label start = new Label("\nPress any key to start", AsteroidsGame.labelStyleNormal);
        start.setPosition(
                AsteroidsGame.WIDTH / 2f - start.getWidth() / 2,
                score.getY() - start.getHeight() - score.getHeight());
        getStage().addActor(start);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean keyDown(int keycode) {
        this.dispose();
        game.setScreen(new GameScreen(game));
        return false;
    }

    private void initMeteors() {
        for (int i = 0; i < 10; i++) {
            float x = MathUtils.random(0, AsteroidsGame.WIDTH);
            float y = MathUtils.random(0, AsteroidsGame.HEIGHT);
            new Meteor(x, y, getStage());
        }
    }
}
