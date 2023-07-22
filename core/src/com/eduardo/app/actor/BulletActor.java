package com.eduardo.app.actor;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.eduardo.app.MainGame;
import com.eduardo.app.util.Assets;
import com.eduardo.app.extructura.Actor;


public class BulletActor extends Actor {

    public static final double LIMIT_NEGATIVE_Z = -10;

    public BulletActor() {
        super(MainGame.assetManager);
        setModel(new ModelInstance(assetManager.get(Assets.MISSILE_OBJ, Model.class)));
        setRadio(0.1f);
        configInitModel();
    }

    public void configInitModel() {
        setTranslate(0, 0, 0);
        setScale(0.5f, 0.5f, 0.5f);
        setRotate(0, 1, 0, 180);
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera, Environment environment) {
        batch.begin(camera);
        batch.render(getModel(), environment);
        batch.end();
    }

    public boolean exceedLimit() {
        return getPosition().z < LIMIT_NEGATIVE_Z;
    }

    @Override
    protected void dispose() {
    }
}
