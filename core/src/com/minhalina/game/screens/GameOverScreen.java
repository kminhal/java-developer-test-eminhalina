package com.minhalina.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.minhalina.game.AsteroidsGame;
import com.minhalina.game.entities.BaseActor;
import com.minhalina.game.entities.Meteor;

public class GameOverScreen extends BaseScreen {
    private final Game game;
    private boolean isNewRecord;

    public GameOverScreen(Game game, int score) {
        super(game);
        this.game = game;
        Preferences prefs = Gdx.app.getPreferences("game.properties");
        int highscore = prefs.getInteger("score");

        if (score > highscore) {
            isNewRecord = true;
            prefs.putInteger("score", score);
            prefs.flush();
        }
        BaseActor space = new BaseActor(0, 0, getStage());
        space.loadTexture("bg.png");
        space.setSize(AsteroidsGame.WIDTH, AsteroidsGame.HEIGHT);
        initMeteors();

        Label gameOver = new Label("Game Over", AsteroidsGame.labelStyleLarge);
        gameOver.setPosition(
                AsteroidsGame.WIDTH / 2f - gameOver.getWidth() / 2,
                AsteroidsGame.HEIGHT / 2f + gameOver.getHeight() / 2);
        getStage().addActor(gameOver);

        Label oldScore = new Label("Highscore: " + highscore, AsteroidsGame.labelStyleNormal);
        oldScore.setPosition(
                AsteroidsGame.WIDTH / 2f - oldScore.getWidth() / 2,
                gameOver.getY() - oldScore.getHeight() - gameOver.getHeight());
        getStage().addActor(oldScore);

        StringBuilder sb = new StringBuilder("Score: " + score);
        if (isNewRecord)
            sb.append("   A NEW RECORD!");
        Label newScore = new Label(sb.toString(), AsteroidsGame.labelStyleNormal);
        newScore.setPosition(
                AsteroidsGame.WIDTH / 2f - newScore.getWidth() / 2,
                oldScore.getY() - newScore.getHeight() - oldScore.getHeight());
        getStage().addActor(newScore);

        Label restart = new Label("\n Press any key to restart", AsteroidsGame.labelStyleNormal);
        restart.setPosition(
                AsteroidsGame.WIDTH / 2f - restart.getWidth() / 2,
                newScore.getY() - restart.getHeight() - newScore.getHeight());
        getStage().addActor(restart);
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
