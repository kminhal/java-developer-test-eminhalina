package com.minhalina.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.minhalina.game.AsteroidsGame;

import java.io.File;

public class PlayerShip extends BaseActor {
    private Fire fire;
    private Shield shield;
    private int shieldPower;
    private boolean isDestroyed;
    private int score;
    private Vector2 angle;

    public PlayerShip(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("entities" + File.separator + "playerShip.png");
        setBoundaryPolygon(8);
        setAcceleration(400);
        setMaxSpeed(250);
        setDeceleration(100);

        fire = new Fire(0, 0, s);
        addActor(fire);
        fire.setPosition(-fire.getWidth(), getHeight() / 2 - fire.getHeight() / 2);

        shield = new Shield(0, 0, s);
        addActor(shield);
        shield.centerAtPosition(getWidth() / 2, getHeight() / 2);

        angle = new Vector2(0, 0);

        shieldPower = 100;
        isDestroyed = false;
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        angle.set(mouseX, AsteroidsGame.HEIGHT - mouseY).sub(getX() + getWidth() / 2, getY() + getHeight() / 2);
        setRotation(angle.angleDeg());

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            accelerateAtAngle(getRotation() + 90);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            accelerateAtAngle(getRotation() - 90);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            accelerateAtAngle(getRotation());
            fire.setVisible(true);
        } else {
            fire.setVisible(false);
        }

        move(dt);
        wrapAroundWorld(AsteroidsGame.WIDTH, AsteroidsGame.HEIGHT);
        shield.setAlpha(shieldPower / 100f);
        if (shieldPower <= 0)
            shield.setVisible(false);
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public Laser shoot() {
        Laser laser = new Laser(0, 0, this.getStage());
        laser.centerAtActor(this);
        laser.setRotation(this.getRotation());
        laser.setMotionAngle(this.getRotation());
        return laser;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void damageShip() {
        if (!isDestroyed) {
            if (shieldPower <= 0) {
                isDestroyed = true;
                return;
            }
            shieldPower -= 50;
        }
    }
}
