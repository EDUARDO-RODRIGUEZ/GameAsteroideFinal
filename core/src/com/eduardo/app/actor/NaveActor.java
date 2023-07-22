package com.eduardo.app.actor;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.eduardo.app.util.Assets;
import com.eduardo.app.MainGame;
import com.eduardo.app.controller.StateNaveController;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.util.Motion;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NaveActor extends Actor {
    private Map<Motion, Boolean> state;
    private StateNaveController stateNaveController;
    private int life = 3;

    public NaveActor() {
        super(MainGame.assetManager);
        state = new ConcurrentHashMap<>();
        setModel(new ModelInstance(assetManager.get(Assets.NAVE_OBJ, Model.class)));
        setRadio(0.2f);
        lauchThread();
        configInitModel();
    }

    public void lauchThread() {
        stateNaveController = new StateNaveController(this, state);
        stateNaveController.start();
    }

    public void configInitModel() {
        setTranslate(0, 0, 0);
        setRotate(0, 1, 0, 180);
        setScale(0.5f, 0.5f, 0.5f);
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera, Environment environment) {
        batch.begin(camera);
        batch.render(getModel(), environment);
        batch.end();
    }

    @Override
    protected void dispose() {
        stateNaveController.finalizar();
    }

    public void changeState(Motion motion, Boolean value) {
        state.put(motion, value);
    }


}
