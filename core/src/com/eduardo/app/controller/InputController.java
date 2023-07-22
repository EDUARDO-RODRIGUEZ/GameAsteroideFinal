package com.eduardo.app.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.eduardo.app.MainGame;
import com.eduardo.app.actor.NaveActor;
import com.eduardo.app.screen.GameScreen;
import com.eduardo.app.screen.MenuScreen;
import com.eduardo.app.util.GraphicControl;
import com.eduardo.app.util.Motion;

public class InputController extends CameraInputController implements InputProcessor {

    private NaveActor naveActor;
    public InputController() {
        super(GameScreen.stage.camera);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.W:
                naveActor.changeState(Motion.UP, true);
                break;
            case Keys.S:
                naveActor.changeState(Motion.DOWN, true);
                break;
            case Keys.A:
                naveActor.changeState(Motion.LEFT, true);
                break;
            case Keys.D:
                naveActor.changeState(Motion.RIGHT, true);
                break;
            case Keys.O:
                naveActor.changeState(Motion.FRONT, true);
                break;
            case Keys.L:
                naveActor.changeState(Motion.BACK, true);
                break;
            case Keys.P:
                naveActor.changeState(Motion.SHOOT, true);
                break;
            case Keys.NUM_1:
                GameScreen.stage.typeCamera = "cameraOne";
                updateCamera(
                        GameScreen.naveActor.getPosition().x,
                        GameScreen.naveActor.getPosition().y,
                        GameScreen.naveActor.getPosition().z + 1f,
                        GameScreen.naveActor.getPosition().x,
                        GameScreen.naveActor.getPosition().y,
                        GameScreen.naveActor.getPosition().z - 1
                );
                break;
            case Keys.NUM_2:
                GameScreen.stage.typeCamera = "cameraTwo";
                updateCamera(
                        (GameScreen.naveActor.getPosition().x + 5),
                        GameScreen.naveActor.getPosition().y,
                        GameScreen.naveActor.getPosition().z,
                        GameScreen.naveActor.getPosition().x,
                        GameScreen.naveActor.getPosition().y,
                        (GameScreen.naveActor.getPosition().z - 5)
                );
                break;
            case Keys.NUM_3:
                GameScreen.stage.typeCamera = "cameraThree";
                updateCamera(
                        GameScreen.naveActor.getPosition().x,
                        (GameScreen.naveActor.getPosition().y + 0.2f),
                        (GameScreen.naveActor.getPosition().z + 0.8f),
                        GameScreen.naveActor.getPosition().x,
                        GameScreen.naveActor.getPosition().y,
                        GameScreen.naveActor.getPosition().z
                );
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.W:
                naveActor.changeState(Motion.UP, false);
                break;
            case Keys.S:
                naveActor.changeState(Motion.DOWN, false);
                break;
            case Keys.A:
                naveActor.changeState(Motion.LEFT, false);
                break;
            case Keys.D:
                naveActor.changeState(Motion.RIGHT, false);
                break;
            case Keys.O:
                naveActor.changeState(Motion.FRONT, false);
                break;
            case Keys.L:
                naveActor.changeState(Motion.BACK, false);
                break;
            case Keys.P:
                naveActor.changeState(Motion.SHOOT, false);
                break;
        }
        return true;
    }

    public void setNaveActor(NaveActor naveActor) {
        this.naveActor = naveActor;
    }

    public void updateCamera(float px, float py, float pz, float lx, float ly, float lz) {
        GameScreen.stage.camera.position.set(px, py, pz);
        GameScreen.stage.camera.lookAt(lx, ly, lz);
        GameScreen.stage.camera.near = 0.1f;
        GameScreen.stage.camera.far = 300f;
        GameScreen.stage.camera.update();
    }
}
