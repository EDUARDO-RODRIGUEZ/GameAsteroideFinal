package com.eduardo.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.eduardo.app.MainGame;
import com.eduardo.app.actor.ContentInfo;
import com.eduardo.app.extructura.Stage;
import com.eduardo.app.actor.NaveActor;
import com.eduardo.app.controller.InputController;
import com.eduardo.app.procesor.ProcesorAsteroide;
import com.eduardo.app.procesor.ProcesorBullet;
import com.eduardo.app.producer.ProducerAsteroid;
import com.eduardo.app.util.Assets;
import com.eduardo.app.util.BaseScreen;
import com.eduardo.app.util.Skybox;

public class GameScreen extends BaseScreen {
    public static Stage stage = new Stage();
    private ProcesorAsteroide procesorAsteroide;
    private ProcesorBullet procesorBullet;
    private InputController inputController;
    private ProducerAsteroid producerAsteroid;
    public static NaveActor naveActor = new NaveActor();
    public static ContentInfo contentInfo;
    public static Sound spaceSound, destroyAsteroideSound, bulletSound, damageSound;
    private Skybox skybox;

    public GameScreen(MainGame mainGame) {
        super(mainGame);
        this.inputController = new InputController();
        this.procesorAsteroide = new ProcesorAsteroide();
        this.procesorBullet = new ProcesorBullet();
        this.producerAsteroid = new ProducerAsteroid();
        contentInfo = new ContentInfo(3, mainGame);
        loadActors();
        loadControllers();
        lauchThreads();
        skybox = new Skybox(Gdx.files.internal("skybox"));
        spaceSound = MainGame.assetManager.get(Assets.SPACE_SOUND);
        destroyAsteroideSound = MainGame.assetManager.get(Assets.DESTROY_ASTEROIDE_SOUND);
        bulletSound = MainGame.assetManager.get(Assets.BULLET_SOUND);
        damageSound = MainGame.assetManager.get(Assets.DAMAGE_SOUND);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
    }

    private void loadControllers() {
        inputController.setNaveActor(naveActor);
        Gdx.input.setInputProcessor(inputController);
    }

    public void loadActors() {
        stage.addActor("nave", naveActor);
        stage.addActor("info", contentInfo);
    }

    public void lauchThreads() {
        procesorBullet.start();
        procesorAsteroide.start();
        producerAsteroid.start();
    }

    @Override
    public void show() {
        long id = spaceSound.play(1);
        spaceSound.setLooping(id, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        skybox.render(stage.camera);
        stage.draw();
    }

    @Override
    public void hide() {
    }

    public void suspender() {
        procesorAsteroide.suspender();
        procesorBullet.suspender();
        producerAsteroid.suspender();
    }

    @Override
    public void dispose() {
        procesorAsteroide.finalizar();
        procesorBullet.finalizar();
        producerAsteroid.finalizar();
        stage.dispose();
        spaceSound.dispose();
    }
}
