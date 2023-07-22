package com.eduardo.app.actor;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.eduardo.app.MainGame;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.util.Assets;

public class AsteroidActor extends Actor {
    public static final double LIMIT_POSITIVE_Z = 1;
    private float pendienteDy;
    private float pendienteDx;

    public AsteroidActor() {
        super(MainGame.assetManager);
        setModel(new ModelInstance(assetManager.get(Assets.ASTEROIDE_OBJ, Model.class)));
        setRadio(0.3f);
        configInitModel();
    }

    private void configInitModel() {
        setTranslate(0, 0, 0);
        setScale(0.2f, 0.2f, 0.2f);
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera, Environment environment) {
        batch.begin(camera);
        batch.render(getModel(), environment);
        batch.end();
    }

    public boolean exceedLimit() {
        return getPosition().z > LIMIT_POSITIVE_Z;
    }

    public void calcuarlPendiente(Vector3 position) {
        pendienteDx = position.x - getPosition().x;
        pendienteDy = position.y - getPosition().y;
    }

    public float getPendienteDy() {
        return pendienteDy;
    }

    public float getPendienteDx() {
        return pendienteDx;
    }

    public void setPendienteDy(float pendienteDy) {
        this.pendienteDy = pendienteDy;
    }

    public void setPendienteDx(float pendienteDx) {
        this.pendienteDx = pendienteDx;
    }

    @Override
    protected void dispose() {
    }
}
