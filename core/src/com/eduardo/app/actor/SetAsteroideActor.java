package com.eduardo.app.actor;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.eduardo.app.MainGame;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.util.Assets;

public class SetAsteroideActor extends Actor {

    public SetAsteroideActor() {
        super(MainGame.assetManager);
        setModel(new ModelInstance(assetManager.get(Assets.EXPLODE_OBJ, Model.class)));
        configInitModel();
    }

    private void configInitModel() {
        setScale(0.1f, 0.1f, 0.1f);
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera, Environment environment) {
        batch.begin(camera);
        batch.render(getModel(), environment);
        batch.end();
    }

    @Override
    protected void dispose() {
    }
}
