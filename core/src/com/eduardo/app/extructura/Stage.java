package com.eduardo.app.extructura;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Stage {
    private Map<String, List<Actor>> actors;
    private ModelBatch modelBatch;
    public static PerspectiveCamera camera;
    public static String typeCamera = "cameraOne";
    private Environment environment;

    public Stage() {
        this.actors = new ConcurrentHashMap<>();
        this.modelBatch = new ModelBatch();
        this.camera = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        configCamera();
    }

    public void configCamera() {
        camera.position.set(0f, 0f, 1f);
        camera.lookAt(0, 0, 0);
        camera.near = 0.1f;
        camera.far = 300f;
        camera.update();
    }

    public synchronized void addActor(String type, Actor actor) {
        List<Actor> lista = actors.get(type);
        if (lista == null) {
            lista = java.util.Collections.synchronizedList(new ArrayList<Actor>());
            actors.put(type, lista);
        }
        lista.add(actor);
    }

    public synchronized void removeActor(String type, Actor actor) {
        List<Actor> lista = actors.get(type);
        if (lista == null) {
            return;
        }
        lista.remove(actor);
    }

    public synchronized List<Actor> getActors(String type) {
        List<Actor> lista = actors.get(type);
        if (lista == null) {
            lista = Collections.synchronizedList(new ArrayList<Actor>());
        }
        return lista;
    }

    public synchronized void draw() {
        for (Map.Entry<String, List<Actor>> entry : actors.entrySet()) {
            for (Actor actor : entry.getValue()) {
                actor.draw(modelBatch, camera, environment);
            }
        }
    }

    public synchronized void dispose() {
        for (Map.Entry<String, List<Actor>> entry : actors.entrySet()) {
            for (Actor actor : entry.getValue()) {
                actor.dispose();
            }
        }
        modelBatch.dispose();
    }
}
