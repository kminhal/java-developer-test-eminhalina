package com.minhalina.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.minhalina.game.AsteroidsGame;
import com.minhalina.game.entities.BaseActor;
import com.minhalina.game.entities.Meteor;
import com.minhalina.game.entities.PlayerShip;

public class GameScreen extends BaseScreen {
    private PlayerShip playerShip;
    private Array<BaseActor> meteorList;
    private Array<BaseActor> laserList;
    private Label score;
    private final Game game;

    public GameScreen(Game game) {
        super(game);
        this.game = game;
        BaseActor space = new BaseActor(0, 0, getStage());
        space.loadTexture("bg.png");
        space.setSize(AsteroidsGame.WIDTH, AsteroidsGame.HEIGHT);

        playerShip = new PlayerShip(0, 0, getStage());
        playerShip.centerAtActor(space);

        meteorList = new Array<>();
        initMeteors();

        laserList = new Array<>();

        score = new Label("Score:", AsteroidsGame.labelStyleNormal);
        score.setPosition(10, AsteroidsGame.HEIGHT - score.getHeight() - 10);
        getStage().addActor(score);
    }

    @Override
    public void update(float dt) {
        for (BaseActor meteor : meteorList) {
            if (meteor.overlaps(playerShip)) {
                playerShip.damageShip();
                if (playerShip.isDestroyed()) {
                    playerShip.remove();
                    this.dispose();
                    game.setScreen(new GameOverScreen(game, playerShip.getScore()));
                } else {
                    changeMeteor(meteor);
                }
            }
            for (BaseActor laser : laserList) {
                if (laser.overlaps(meteor)) {
                    laser.remove();
                    laserList.removeValue(laser, false);
                    changeMeteor(meteor);
                    playerShip.incrementScore();
                }
            }
            score.setText("Score: " + playerShip.getScore());
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            BaseActor laser = playerShip.shoot();
            laserList.add(laser);
        }
        return false;
    }

    public void initMeteors() {
        for (int i = 0; i < 10; i++) {
            meteorList.add(createMeteor());
        }
    }

    private BaseActor createMeteor() {
        Vector2 v = new Vector2();
        float x = MathUtils.random(AsteroidsGame.WIDTH);
        float y = MathUtils.random(AsteroidsGame.HEIGHT);
        float dx = x - playerShip.getX();
        float dy = y - playerShip.getY();
        float dist = v.set(dx, dy).len();

        while (dist < 150) {
            x = MathUtils.random(AsteroidsGame.WIDTH);
            y = MathUtils.random(AsteroidsGame.HEIGHT);
            dx = x - playerShip.getX();
            dy = y - playerShip.getY();
            dist = v.set(dx, dy).len();
        }
        return new Meteor(x, y, getStage());
    }

    private void changeMeteor(BaseActor meteor) {
        meteor.remove();
        meteorList.removeValue(meteor, false);
        BaseActor newMeteor = createMeteor();
        meteorList.add(newMeteor);
    }
}
