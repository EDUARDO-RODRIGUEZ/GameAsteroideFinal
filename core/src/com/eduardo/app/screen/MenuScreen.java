package com.eduardo.app.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Align;
import com.eduardo.app.MainGame;
import com.eduardo.app.component.BuilderTextButton;
import com.eduardo.app.util.Assets;
import com.eduardo.app.util.BaseScreen;
import jdk.tools.jmod.Main;

public class MenuScreen extends BaseScreen {

    private Stage stage;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private BuilderTextButton builderTextButton;
    private TextButton btnPlay, btnOptions, btnExit;
    private Label title;
    private Sound select;
    private String buttonSelect;

    public MenuScreen(MainGame mainGame) {
        super(mainGame);
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        backgroundTexture = MainGame.assetManager.get(Assets.BACKGROUND_MENU);
        this.builderTextButton = new BuilderTextButton();
        this.select = MainGame.assetManager.get(Assets.BUTTON_SELECT_SOUND);
        buildButtons();
        loadEventsClicks();
        loadEventFocus();
        loadElements();
    }

    private void buildButtons() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        builderTextButton.getParameter().color = Color.WHITE;
        builderTextButton.getParameter().size = 60;

        labelStyle.font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
        title = new Label(" Space Invaders ", labelStyle);
        title.setAlignment(Align.top);

        builderTextButton.setText("Play");
        builderTextButton.getParameter().color = Color.PURPLE;
        builderTextButton.getParameter().size = 35;
        builderTextButton.getTextButtonStyle().font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
        btnPlay = builderTextButton.getTextButton();

        builderTextButton.setText("Options");
        builderTextButton.getParameter().color = Color.WHITE;
        builderTextButton.getParameter().size = 35;
        builderTextButton.getTextButtonStyle().font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
        btnOptions = builderTextButton.getTextButton();

        builderTextButton.setText("Exit");
        builderTextButton.getParameter().color = Color.WHITE;
        builderTextButton.getParameter().size = 35;
        builderTextButton.getTextButtonStyle().font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
        btnExit = builderTextButton.getTextButton();
    }

    private void loadEventsClicks() {
        btnPlay.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Screen screen = new GameScreen(mainGame);
                MainGame.screens.put("gameScreen", screen);
                mainGame.setScreen(screen);
            }
        });

        btnOptions.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
            }
        });

        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                mainGame.dispose();
                System.exit(0);
            }
        });
    }

    private void loadElements() {
        Table contentTitle = new Table();
        contentTitle.setFillParent(true);
        contentTitle.top();
        contentTitle.pad(10);
        contentTitle.add(title);

        Table contentCenter = new Table();
        contentCenter.setFillParent(true);
        contentCenter.center();
        contentCenter.add(btnPlay);
        contentCenter.row();
        contentCenter.add(btnOptions);
        contentCenter.row();
        contentCenter.add(btnExit);

        stage.addActor(contentCenter);
        stage.addActor(contentTitle);
        Gdx.input.setInputProcessor(stage);
    }

    private void loadEventFocus() {
        btnPlay.addListener(new FocusListener() {
            @Override
            public boolean handle(Event event) {
                if (buttonSelect == null || buttonSelect != "btnPlay") {
                    select.play();
                    buttonSelect = "btnPlay";
                }
                clearColorsButtons();
                builderTextButton.getParameter().color = Color.PURPLE;
                btnPlay.getStyle().font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
                btnPlay.setStyle(builderTextButton.getTextButtonStyle());
                return true;
            }
        });

        btnOptions.addListener(new FocusListener() {
            @Override
            public boolean handle(Event event) {
                if (buttonSelect == null || buttonSelect != "btnOptions") {
                    select.play();
                    buttonSelect = "btnOptions";
                }
                clearColorsButtons();
                builderTextButton.getParameter().color = Color.PURPLE;
                btnOptions.getStyle().font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
                btnOptions.setStyle(builderTextButton.getTextButtonStyle());
                return true;
            }
        });

        btnExit.addListener(new FocusListener() {
            @Override
            public boolean handle(Event event) {
                if (buttonSelect == null || buttonSelect != "btnExit") {
                    select.play();
                    buttonSelect = "btnExit";
                }
                clearColorsButtons();
                builderTextButton.getParameter().color = Color.PURPLE;
                btnExit.getStyle().font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
                btnExit.setStyle(builderTextButton.getTextButtonStyle());
                return true;
            }
        });
    }

    private void clearColorsButtons() {
        builderTextButton.getParameter().color = Color.WHITE;
        builderTextButton.getTextButtonStyle().font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());
        btnPlay.setStyle(builderTextButton.getTextButtonStyle());
        btnOptions.setStyle(builderTextButton.getTextButtonStyle());
        btnExit.setStyle(builderTextButton.getTextButtonStyle());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        backgroundTexture.dispose();
    }

}
